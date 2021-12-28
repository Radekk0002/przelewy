package com.przelewy.banks;

import java.util.ArrayList;
import java.util.List;

public class AllBanks {
  private static final List<Bank> banks = new ArrayList<>();

  public static void addBank(Bank bank){
    AllBanks.banks.add(bank);
  }

  public static Bank getBankUsingPESEL(String PESEL){
    for (Bank bank : banks) {
      if(bank.customerExist(PESEL)) return bank;
    }
    return null;
  }
  public static Bank getBankUsingPhone(String phoneNo){
    for (Bank bank : banks) {
      if(bank.phoneExist(phoneNo)) return bank;
    }
    return null;
  }


}
