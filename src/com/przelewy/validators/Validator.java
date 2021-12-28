package com.przelewy.validators;

public interface Validator<T> {
  public boolean validate(T smth);
}
