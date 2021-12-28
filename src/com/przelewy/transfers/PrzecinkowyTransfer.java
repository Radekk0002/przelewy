package com.przelewy.transfers;

public class PrzecinkowyTransfer implements Transfer{
  String title;
  String PESEL;
  String type;
  double amount;

  public PrzecinkowyTransfer(String title, String PESEL, String type, double amount){
    this.title = title;
    this.PESEL = PESEL;
    this.type = type;
    this.amount = amount;
  }

  @Override
  public String getPESEL() {
    return PESEL;
  }

  @Override
  public double getAmount() {
    return amount;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getType() {
    return type;
  }
}
