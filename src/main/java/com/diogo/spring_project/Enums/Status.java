package com.diogo.spring_project.Enums;

public enum Status {
  ATIVO("Ativo"), INATIVO("Inativo"), TRANCADO("Trancado"), CANCELADO("Cancelado");

  private String status;

  private Status(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}