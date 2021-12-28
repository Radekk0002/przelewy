package com.przelewy.transfers;

public class ZwierzecyMobileTransfer  implements Transfer{
  private final String format;
  private final String type;
  private final String phoneNo;
  private final double amount;

  public ZwierzecyMobileTransfer(String format, String type, String phoneNo, double amount) {
    this.format = format;
    this.type = type;
    this.phoneNo = phoneNo;
    this.amount = amount;
  }

  @Override
  public String getPESEL() {
    return null;
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
    return null;
  }

  public String getFormat() {
    return format;
  }

  public String getPhoneNo() {
    return phoneNo;
  }
}
