package com.przelewy.customers;

public class Customer {
  String name;
  String surname;
  String PESEL;
  String bankNo;

  public Customer(String name, String surname, String PESEL,String bankNo){
    this.name = name;
    this.surname = surname;
    this.PESEL = PESEL;
    this.bankNo = bankNo;
  }
}
