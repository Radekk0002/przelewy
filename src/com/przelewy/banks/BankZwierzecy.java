package com.przelewy.banks;

import com.przelewy.customers.Customer;
import com.przelewy.customers.ZwierzecyCustomer;
import com.przelewy.transfers.PrzecinkowyTransfer;
import com.przelewy.transfers.Transfer;
import com.przelewy.transfers.ZwierzecyMobileTransfer;
import com.przelewy.transfers.ZwierzecyTransfer;
import com.przelewy.validators.BankPrzecinkowyValidator;
import com.przelewy.validators.BankZwierzecyValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BankZwierzecy extends Bank<ZwierzecyTransfer, BankZwierzecyValidator>{

  public BankZwierzecy(){
    customers = new HashMap<>();
    transfers = new ArrayList<>();
    amountTransfered = failedTransfers = successfulTransfers = 0;
    validator = new BankZwierzecyValidator();
  }

  @Override
  public boolean loadCustomers(String dbPath) {
    try {
      List<String> allLines = Files.readAllLines(Paths.get(dbPath));
      for (String line : allLines) {
        String[] tmp = line.split("\\|");
        customers.put(tmp[6], new ZwierzecyCustomer(tmp[6], tmp[1], tmp[2],tmp[3],tmp[4],tmp[5],tmp[6],tmp[7]));
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean loadTransfers(String transfersPath) {
    try {
      List<String> allLines = Files.readAllLines(Paths.get(transfersPath));
      for (String line : allLines) {
        String[] tmp = line.split("");
        String format = String.join("",Arrays.copyOfRange(tmp, 0, 15));
        String type = String.join("",Arrays.copyOfRange(tmp, 15, 25));

        if(format.contains(Transfer.KLASYCZNY.name())) {
          int nameEnd = indexOf(tmp, 26, " ");
          String receiverName = String.join("",Arrays.copyOfRange(tmp, 25, nameEnd));
          int surnameEnd = indexOf(tmp, nameEnd+1, " ");
          String receiverSurname = String.join("",Arrays.copyOfRange(tmp, nameEnd+1, surnameEnd));
          int peselEnd = indexOf(tmp, surnameEnd+1, " ");
          String pesel = String.join("",Arrays.copyOfRange(tmp, surnameEnd+1, peselEnd));
          int amountEnd = indexOf(tmp, peselEnd+1, " ");
          String amount = String.join("",Arrays.copyOfRange(tmp, peselEnd+1, amountEnd));
          String name = String.join("",Arrays.copyOfRange(tmp, amountEnd+1, amountEnd+1+20));
          String surname = String.join("",Arrays.copyOfRange(tmp, amountEnd+1+20, amountEnd+1+20+20));
          String title = String.join("",Arrays.copyOfRange(tmp, amountEnd+1+20+20, tmp.length));
          transfers.add(new ZwierzecyTransfer(format, type, receiverName, receiverSurname, pesel, Double.parseDouble(amount), name, surname, title));
        }
        else{
          int phoneNoEnd = indexOf(tmp, 25, " ");
          String phoneNo = String.join("",Arrays.copyOfRange(tmp, 25, phoneNoEnd));
          String amount = String.join("",Arrays.copyOfRange(tmp, phoneNoEnd+1, tmp.length));
          transfers.add(new ZwierzecyMobileTransfer(format, type, phoneNo, Double.parseDouble(amount)));
        }
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public void doTransfers(){
    for (com.przelewy.transfers.Transfer transfer : transfers) {
      com.przelewy.transfers.Transfer transf = transfer;
      if(validator.validate(transf)){
        Bank bank;
        if((bank = AllBanks.getBankUsingPESEL(transf.getPESEL())) != null || (bank = AllBanks.getBankUsingPhone(((ZwierzecyMobileTransfer)transf).getPhoneNo())) != null){
          successfulTransfers += 1;
          amountTransfered += transf.getAmount();
        }
        else
          failedTransfers += 1;
      }
      else
        failedTransfers += 1;
    }
    generateReport();

    amountTransfered = successfulTransfers = failedTransfers = 0;
  }

  @Override
  public boolean phoneExist(String phoneNo) {
    for (Map.Entry<String, Customer> entry : customers.entrySet()) {
      if (((ZwierzecyCustomer)entry.getValue()).getPhoneNumber().equals(phoneNo)) return true;
    }
    return false;
  }
//
//  @Override
//  public boolean customerExist(String PESEL) {
//    return customers.containsKey(PESEL);
//  }

  private <T> int indexOf(T[] arr, int from, T c){
    for (int i = from; i < arr.length; i++) {
      if(arr[i].equals(c)) return i;
    }
    return -1;
  }
  public enum Transfer{
    KLASYCZNY,TELEFONICZNY
  }
}
