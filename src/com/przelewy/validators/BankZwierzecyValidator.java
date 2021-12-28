package com.przelewy.validators;

import com.przelewy.banks.BankZwierzecy;
import com.przelewy.transfers.Transfer;
import com.przelewy.transfers.ZwierzecyMobileTransfer;
import com.przelewy.transfers.ZwierzecyTransfer;

public class BankZwierzecyValidator implements Validator<Transfer>{
  @Override
  public boolean validate(Transfer transfer) {
    if(transfer instanceof ZwierzecyTransfer && validateStandardTransfer((ZwierzecyTransfer) transfer)) return true;
    else if(transfer instanceof ZwierzecyMobileTransfer && validateMobileTransfer((ZwierzecyMobileTransfer) transfer)) return true;
    return false;
  }

  private boolean validateStandardTransfer(ZwierzecyTransfer transfer){
    if(transfer.getFormat().length() != 15 || !transfer.getFormat().contains(BankZwierzecy.Transfer.KLASYCZNY.name())) return false;
    if(transfer.getType().length() != 10) return false;
    if(transfer.getReceiverName().length() < 1) return false;
    if(transfer.getReceiverSurname().length() < 1) return false;
    if(transfer.getPESEL().length() != 11) return false;
    if(transfer.getAmount() <= 0) return false;
    if(transfer.getName().length() != 20) return false;
    if(transfer.getSurname().length() != 20) return false;
    return transfer.getTitle().length() >= 1;
  }

  private boolean validateMobileTransfer(ZwierzecyMobileTransfer transfer){
    if(transfer.getFormat().length() != 15 || !transfer.getFormat().contains(BankZwierzecy.Transfer.TELEFONICZNY.name())) return false;
    if(transfer.getType().length() != 10) return false;
    String[] tmp = transfer.getPhoneNo().split("-");
    if(tmp.length != 3 || tmp[0].length() != 3
        || tmp[1].length() != 3 || tmp[2].length() != 3  ) return false;
    return !(transfer.getAmount() <= 0);
  }
}
