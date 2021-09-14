package com.diogo.spring_project.Enums;

public enum Rotation {
  MANHA("Manhã"),TARDE("Tarde"),NOITE("Noite");
  private String name;
  private Rotation(String name){
    this.name = name;
  }
  public String getName() {
    return name;
  }
}
