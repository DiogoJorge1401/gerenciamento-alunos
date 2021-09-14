package com.diogo.spring_project.Enums;

public enum Course {
  ADMINISTRACAO("Administração"), INFORMATICA("Informática"), CONTABILIDADE("Contabilidade"), PROGRAMACAO("Programação"),
  ENFERMAGEM("Enfermagem");

  private String curse;

  private Course(String curse) {
    this.curse = curse;
  }
  public String getCurse() {
    return curse;
  }
}
