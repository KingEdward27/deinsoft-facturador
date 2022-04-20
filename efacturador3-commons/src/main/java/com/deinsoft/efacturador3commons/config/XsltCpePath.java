package com.deinsoft.efacturador3commons.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class XsltCpePath
{
  @NotEmpty
  @Value("${xsltcpe-path.factura}")
  private String factura;
  @NotEmpty
  @Value("${xsltcpe-path.boleta}")
  private String boleta;
  @NotEmpty
  @Value("${xsltcpe-path.notaCredito}")
  private String notaCredito;
  @NotEmpty
  @Value("${xsltcpe-path.notaDebito}")
  private String notaDebito;
  
  @NotEmpty
  @Value("${xsltcpe-path.resumenBoleta}")
  private String resumenBoleta;
  
  @NotEmpty
  @Value("${xsltcpe-path.resumenAnulado}")
  private String resumenAnulado;
  
  @NotEmpty
  @Value("${xsltcpe-path.retencion}")
  private String retencion;
  
  @NotEmpty
  @Value("${xsltcpe-path.percepcion}")
  private String percepcion;
  
  @NotEmpty
  @Value("${xsltcpe-path.guia}")
  private String guia;
  
  @NotEmpty
  @Value("${xsltcpe-path.resumenReversion}")
  private String resumenReversion;
  
  @Value("${xsltcpe-path.servicioPublico}")
  @NotEmpty
  private String servicioPublico;
  
  @Value("${xsltcpe-path.facturaXsd}")
  @NotEmpty
  private String facturaXsd;
  
  
  @NotEmpty
  @Value("${xsltcpe-path.boletaXsd}")
  private String boletaXsd;
  
  @NotEmpty
  @Value("${xsltcpe-path.notaCreditoXsd}")
  private String notaCreditoXsd;
  
  @NotEmpty
  @Value("${xsltcpe-path.notaDebitoXsd}")
  private String notaDebitoXsd;

  @NotEmpty
  @Value("${xsltcpe-path.resumenBoletaXsd}")
  private String resumenBoletaXsd;
  @NotEmpty
  @Value("${xsltcpe-path.resumenAnuladoXsd}")
  private String resumenAnuladoXsd;
  
  @NotEmpty
  @Value("${xsltcpe-path.retencionXsd}")
  private String retencionXsd;
  
  @NotEmpty
  @Value("${xsltcpe-path.percepcionXsd}")
  private String percepcionXsd;
  
  @NotEmpty
  @Value("${xsltcpe-path.guiaXsd}")
  private String guiaXsd;
  
  @NotEmpty
  @Value("${xsltcpe-path.resumenReversionXsd}")
  private String resumenReversionXsd;
  
  @NotEmpty
  @Value("${plazoBoleta}")
  private String plazoBoleta;
  
  
  @JsonProperty
  public String getPlazoBoleta() {
    return this.plazoBoleta;
  }
  
  @JsonProperty
  public void setPlazoBoleta(String plazoBoleta) {
    this.plazoBoleta = plazoBoleta;
  }
  
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
  
  @JsonProperty
  public String getFacturaXsd() {
    return this.facturaXsd;
  }
  
  @JsonProperty
  public void setFacturaXsd(String facturaXsd) {
    this.facturaXsd = facturaXsd;
  }
  
  @JsonProperty
  public String getBoletaXsd() {
    return this.boletaXsd;
  }
  
  @JsonProperty
  public void setBoletaXsd(String boletaXsd) {
    this.boletaXsd = boletaXsd;
  }
  
  @JsonProperty
  public String getNotaCreditoXsd() {
    return this.notaCreditoXsd;
  }
  
  @JsonProperty
  public void setNotaCreditoXsd(String notaCreditoXsd) {
    this.notaCreditoXsd = notaCreditoXsd;
  }
  
  @JsonProperty
  public String getNotaDebitoXsd() {
    return this.notaDebitoXsd;
  }
  
  @JsonProperty
  public void setNotaDebitoXsd(String notaDebitoXsd) {
    this.notaDebitoXsd = notaDebitoXsd;
  }
  
  @JsonProperty
  public String getResumenBoletaXsd() {
    return this.resumenBoletaXsd;
  }
  
  @JsonProperty
  public void setResumenBoletaXsd(String resumenBoletaXsd) {
    this.resumenBoletaXsd = resumenBoletaXsd;
  }
  
  @JsonProperty
  public String getResumenAnuladoXsd() {
    return this.resumenAnuladoXsd;
  }
  
  @JsonProperty
  public void setResumenAnuladoXsd(String resumenAnuladoXsd) {
    this.resumenAnuladoXsd = resumenAnuladoXsd;
  }
  
  @JsonProperty
  public String getRetencionXsd() {
    return this.retencionXsd;
  }
  
  @JsonProperty
  public void setRetencionXsd(String retencionXsd) {
    this.retencionXsd = retencionXsd;
  }
  
  @JsonProperty
  public String getPercepcionXsd() {
    return this.percepcionXsd;
  }
  
  @JsonProperty
  public void setPercepcionXsd(String percepcionXsd) {
    this.percepcionXsd = percepcionXsd;
  }
  
  @JsonProperty
  public String getGuiaXsd() {
    return this.guiaXsd;
  }
  
  @JsonProperty
  public void setGuiaXsd(String guiaXsd) {
    this.guiaXsd = guiaXsd;
  }
  
  @JsonProperty
  public String getResumenReversionXsd() {
    return this.resumenReversionXsd;
  }
  
  @JsonProperty
  public void setResumenReversionXsd(String resumenReversionXsd) {
    this.resumenReversionXsd = resumenReversionXsd;
  }

    @Override
    public String toString() {
        return "XsltCpePath{" + "factura=" + factura + ", boleta=" + boleta + ", notaCredito=" + notaCredito + ", notaDebito=" + notaDebito + ", resumenBoleta=" + resumenBoleta + ", resumenAnulado=" + resumenAnulado + ", retencion=" + retencion + ", percepcion=" + percepcion + ", guia=" + guia + ", resumenReversion=" + resumenReversion + ", servicioPublico=" + servicioPublico + ", facturaXsd=" + facturaXsd + ", boletaXsd=" + boletaXsd + ", notaCreditoXsd=" + notaCreditoXsd + ", notaDebitoXsd=" + notaDebitoXsd + ", resumenBoletaXsd=" + resumenBoletaXsd + ", resumenAnuladoXsd=" + resumenAnuladoXsd + ", retencionXsd=" + retencionXsd + ", percepcionXsd=" + percepcionXsd + ", guiaXsd=" + guiaXsd + ", resumenReversionXsd=" + resumenReversionXsd + ", plazoBoleta=" + plazoBoleta + '}';
    }
  
  
}
