package com.przelewy.validators;

import com.przelewy.transfers.PrzecinkowyTransfer;

public class BankPrzecinkowyValidator implements Validator<PrzecinkowyTransfer> {

  @Override
  public boolean validate(PrzecinkowyTransfer transfer) {
    if(transfer.getType().length() > 10 || transfer.getType().length() == 0) return false;
    if(transfer.getPESEL().length() != 11) return false;
    if(transfer.getTitle().length() > 20) return false;
    if(transfer.getAmount() <= 0) return false;
    return true;
  }
}
