package com.przelewy.customers;

public class ZwierzecyCustomer extends Customer{
  String zipCode;
  String animal;
  String animalNick;
  String phoneNumber;

  public ZwierzecyCustomer(String zipCode, String animal, String animalNick, String phoneNumber, String name, String surname, String PESEL, String bankNo) {
    super(name, surname, PESEL, bankNo);

    this.zipCode = zipCode;
    this.animal = animal;
    this.animalNick = animalNick;
    this.phoneNumber = phoneNumber;
  }

  public String getAnimal() {
    return animal;
  }

  public String getAnimalNick() {
    return animalNick;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getZipCode() {
    return zipCode;
  }
}
