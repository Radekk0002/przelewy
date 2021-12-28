package com.przelewy.transfers;

public class ZwierzecyTransfer implements Transfer{

  private final String format;
  private final String type;
  private final String receiverName;
  private final String receiverSurname;
  private final String PESEL;
  private final double amount;
  private final String name;
  private final String surname;
  private final String title;

  public ZwierzecyTransfer(String format, String type, String receiverName, String receiverSurname, String PESEL, double amount, String name, String surname, String title){

    this.format = format;
    this.type = type;
    this.receiverName = receiverName;
    this.receiverSurname = receiverSurname;
    this.PESEL = PESEL;
    this.amount = amount;
    this.name = name;
    this.surname = surname;
    this.title = title;
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
  public String getType() {
    return type;
  }

  @Override
  public String getTitle() {
    return title;
  }

  public String getReceiverSurname() {
    return receiverSurname;
  }

  public String getReceiverName() {
    return receiverName;
  }

  public String getFormat() {
    return format;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }
}
