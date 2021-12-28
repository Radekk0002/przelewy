package com.przelewy;

import com.przelewy.banks.AllBanks;
import com.przelewy.banks.Bank;
import com.przelewy.banks.BankPrzecinkowy;
import com.przelewy.banks.BankZwierzecy;

public class Main {

    public static void main(String[] args) {
        Bank bankPrzecinkowy = new BankPrzecinkowy();
        Bank bankZwierzecy = new BankZwierzecy();
        AllBanks.addBank(bankPrzecinkowy);
        AllBanks.addBank(bankZwierzecy);
        bankPrzecinkowy.loadCustomers("./Data/bankPrzecinkowy/db.txt");
        bankPrzecinkowy.loadTransfers("./Data/bankPrzecinkowy/przelewy.txt");
        bankPrzecinkowy.doTransfers();

        bankZwierzecy.loadCustomers("./Data/bankZwierzecy/db.txt");
        bankZwierzecy.loadTransfers("./Data/bankZwierzecy/przelewy.txt");
        bankZwierzecy.doTransfers();
    }
}
