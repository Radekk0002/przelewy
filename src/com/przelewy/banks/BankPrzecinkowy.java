package com.przelewy.banks;

import com.przelewy.customers.Customer;
import com.przelewy.transfers.PrzecinkowyTransfer;
import com.przelewy.validators.BankPrzecinkowyValidator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BankPrzecinkowy extends Bank<PrzecinkowyTransfer, BankPrzecinkowyValidator> {

  public BankPrzecinkowy(){
    customers = new HashMap<>();
    transfers = new ArrayList<>();
    amountTransfered = failedTransfers = successfulTransfers = 0;
    validator = new BankPrzecinkowyValidator();
  }

  @Override
  public boolean loadCustomers(String dbPath) {
    try {
      List<String> allLines = Files.readAllLines(Paths.get(dbPath));
      for (String line : allLines) {
        String[] tmp = line.split(",");
        customers.put(tmp[3], new Customer(tmp[1], tmp[2], tmp[3], tmp[0]));
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
        String[] tmp = line.split(",");
        transfers.add(new PrzecinkowyTransfer(tmp[2], tmp[1], tmp[0], Double.parseDouble(tmp[3])));
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean phoneExist(String phoneNo) {
    return false;
  }
//
//  @Override
//  public boolean customerExist(String PESEL) {
//    return customers.containsKey(PESEL);
//  }
}
