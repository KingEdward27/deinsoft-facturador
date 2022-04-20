package com.deinsoft.efacturador3commons.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;


public class XsdCpePath
{
  @NotEmpty
  private String factura;
  @NotEmpty
  private String boleta;
  @NotEmpty
  private String notaCredito;
  @NotEmpty
  private String notaDebito;
  @NotEmpty
  private String resumenBoleta;
  @NotEmpty
  private String resumenAnulado;
  @NotEmpty
  private String retencion;
  @NotEmpty
  private String percepcion;
  @NotEmpty
  private String guia;
  @NotEmpty
  private String resumenReversion;
  @NotEmpty
  private String servicioPublico;
  
  @JsonProperty
  public String getFactura() {
    return this.factura;
  }
  
  @JsonProperty
  public void setFactura(String factura) {
    this.factura = factura;
  }

  
  @JsonProperty
  public String getBoleta() {
    return this.boleta;
  }
  
  @JsonProperty
  public void setBoleta(String boleta) {
    this.boleta = boleta;
  }
  
  @JsonProperty
  public String getNotaCredito() {
    return this.notaCredito;
  }
  
  @JsonProperty
  public void setNotaCredito(String notaCredito) {
    this.notaCredito = notaCredito;
  }
  
  @JsonProperty
  public String getNotaDebito() {
    return this.notaDebito;
  }
  
  @JsonProperty
  public void setNotaDebito(String notaDebito) {
    this.notaDebito = notaDebito;
  }
  
  @JsonProperty
  public String getResumenBoleta() {
    return this.resumenBoleta;
  }
  
  @JsonProperty
  public void setResumenBoleta(String resumenBoleta) {
    this.resumenBoleta = resumenBoleta;
  }
  
  @JsonProperty
  public String getResumenAnulado() {
    return this.resumenAnulado;
  }
  
  @JsonProperty
  public void setResumenAnulado(String resumenAnulado) {
    this.resumenAnulado = resumenAnulado;
  }
  
  @JsonProperty
  public String getRetencion() {
    return this.retencion;
  }
  
  @JsonProperty
  public void setRetencion(String retencion) {
    this.retencion = retencion;
  }
  
  @JsonProperty
  public String getPercepcion() {
    return this.percepcion;
  }
  
  @JsonProperty
  public void setPercepcion(String percepcion) {
    this.percepcion = percepcion;
  }
  
  @JsonProperty
  public String getGuia() {
    return this.guia;
  }
  
  @JsonProperty
  public void setGuia(String guia) {
    this.guia = guia;
  }
  
  @JsonProperty
  public String getResumenReversion() {
    return this.resumenReversion;
  }
  
  @JsonProperty
  public void setResumenReversion(String resumenReversion) {
    this.resumenReversion = resumenReversion;
  }
  
  @JsonProperty
  public String getServicioPublico() {
    return this.servicioPublico;
  }
  
  @JsonProperty
  public void setServicioPublico(String servicioPublico) {
    this.servicioPublico = servicioPublico;
  }
}
