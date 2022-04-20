package com.deinsoft.efacturador3commons.model;

public class Contribuyente {
  private String nombreComercial;
  private String numRuc;
  private String razonSocial;
  private Direccion direccion;
  
  public String getNombreComercial() { return this.nombreComercial; }
  public String getNumRuc() { return this.numRuc; } public String getRazonSocial() {
    return this.razonSocial;
  } public Direccion getDireccion() {
    return this.direccion;
  }


  
  public Contribuyente(String numRuc, String nombreComercial, String razonSocial, Direccion direccion) {
    this.numRuc = numRuc;
    this.nombreComercial = nombreComercial;
    this.razonSocial = razonSocial;
    this.direccion = direccion;
  }
}
