package com.deinsoft.efacturador3commons.model;

public class Direccion {
  private String ubigeo;
  private String direccion;
  private String depar;
  
  public String getUbigeo() {
    return this.ubigeo; } private String provin; private String distr; private String urbaniza; public String getDireccion() {
    return this.direccion; }
  public String getDepar() { return this.depar; }
  public String getProvin() { return this.provin; }
  public String getDistr() { return this.distr; } public String getUrbaniza() {
    return this.urbaniza;
  }
  
  public Direccion(String direccion, String urbaniza, String ubigeo, String depar, String provin, String distr) {
    this.direccion = direccion;
    this.urbaniza = urbaniza;
    this.ubigeo = ubigeo;
    this.depar = depar;
    this.provin = provin;
    this.distr = distr;
  }
}