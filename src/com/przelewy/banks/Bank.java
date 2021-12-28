package com.przelewy.banks;

import com.przelewy.customers.Customer;
import com.przelewy.transfers.PrzecinkowyTransfer;
import com.przelewy.transfers.Transfer;
import com.przelewy.validators.Validator;

import java.io.*;
import java.util.List;
import java.util.Map;

public abstract class Bank<T extends Transfer, V extends Validator> {
  Map<String, Customer> customers;
  List<Transfer> transfers;
  V validator;
  long failedTransfers;
  long successfulTransfers;
  double amountTransfered;

  public abstract boolean loadCustomers(String dbPath);

  public abstract boolean loadTransfers(String transfersPath);

  public boolean customerExist(String PESEL) {
    return customers.containsKey(PESEL);
  }

  public abstract boolean phoneExist(String phoneNo);

  public void doTransfers(){
    for (Transfer transfer : transfers) {
      T transf = (T)transfer;
      if(validator.validate(transf)){
        Bank bank;
        if((bank = AllBanks.getBankUsingPESEL(transf.getPESEL())) != null){
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

  protected void generateReport(){
    try (PrintWriter writer = new PrintWriter("Raport" + this.getClass().getSimpleName() + ".txt", "UTF-8")) {
      writer.println("Liczba udanych transakcji: " + successfulTransfers);
      writer.println("Liczba nieudanych transakcji: " + failedTransfers);
      writer.println("Łączna przelana kwota " + amountTransfered);
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
