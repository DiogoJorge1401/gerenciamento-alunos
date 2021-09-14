package com.diogo.spring_project.Exceptions;

public class EmailAlredyExistsException extends Exception{

  public EmailAlredyExistsException(String message) {
    super(message);
  }
}
