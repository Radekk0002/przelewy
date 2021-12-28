package com.przelewy.transfers;

public interface Transfer {
  public String getPESEL();
  public double getAmount();
  public String getType();
  public String getTitle();
}
