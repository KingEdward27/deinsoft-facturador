/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author EDWARD-PC
 */
@Entity(name = "resumenDiario")
@Table(name = "resumen_diario")
public class ResumenDiario {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "resumen_diario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Valid
    @OneToOne
    @JoinColumn(name = "empresa_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Empresa empresa;
    
    @Column(name = "fecha_emision")
    private Date fechaEmision;
    
    @Column(name = "fecha_resumen")
    private Date fechaResumen;
    
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    
    @Column(name = "customization_id")
    private String customizationId;

    @Column(name = "ind_situacion")
    private String indSituacion;
    
    @Column(name = "fecha_envio")
    private Date fechaEnvio;
    
    private Date FechaGenXml;
    
    @Column(name = "observacion_envio")
    private String observacionEnvio;
    
    @Column(name = "ticket_operacion")
    private long ticketOperacion;
    
    @Column(name = "ticket_sunat")
    private String ticketSunat;
    
    @Column(name = "xml_hash")
    private String xmlHash;
    
    @OneToMany(mappedBy = "resumenDiario", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = {"resumenDiario"}, allowSetters = true)
    private List<ResumenDiarioDet> listResumenDiarioDet;
    
    @OneToMany(mappedBy = "resumenDiario", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = {"resumenDiario"}, allowSetters = true)
    private List<ResumenDiarioTax> listResumenDiarioTax;
    
    public void addResumenDiarioDet(ResumenDiarioDet item) {
        item.setResumenDiario(this);
    }
    public void addResumenDiarioTax(ResumenDiarioTax item) {
        item.setResumenDiario(this);
    }
    
    public ResumenDiario() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaResumen() {
        return fechaResumen;
    }

    public void setFechaResumen(Date fechaResumen) {
        this.fechaResumen = fechaResumen;
    }



    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public List<ResumenDiarioDet> getListResumenDiarioDet() {
        return listResumenDiarioDet;
    }

    public void setListResumenDiarioDet(List<ResumenDiarioDet> listResumenDiarioDet) {
        this.listResumenDiarioDet = listResumenDiarioDet;
    }

    public String getCustomizationId() {
        return customizationId;
    }

    public void setCustomizationId(String customizationId) {
        this.customizationId = customizationId;
    }

    public String getIndSituacion() {
        return indSituacion;
    }

    public void setIndSituacion(String indSituacion) {
        this.indSituacion = indSituacion;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaGenXml() {
        return FechaGenXml;
    }

    public void setFechaGenXml(Date FechaGenXml) {
        this.FechaGenXml = FechaGenXml;
    }

    public String getObservacionEnvio() {
        return observacionEnvio;
    }

    public void setObservacionEnvio(String observacionEnvio) {
        this.observacionEnvio = observacionEnvio;
    }

    public long getTicketOperacion() {
        return ticketOperacion;
    }

    public void setTicketOperacion(long ticketOperacion) {
        this.ticketOperacion = ticketOperacion;
    }

    public String getTicketSunat() {
        return ticketSunat;
    }

    public void setTicketSunat(String ticketSunat) {
        this.ticketSunat = ticketSunat;
    }

    public String getXmlHash() {
        return xmlHash;
    }

    public void setXmlHash(String xmlHash) {
        this.xmlHash = xmlHash;
    }

    public List<ResumenDiarioTax> getListResumenDiarioTax() {
        return listResumenDiarioTax;
    }

    public void setListResumenDiarioTax(List<ResumenDiarioTax> listResumenDiarioTax) {
        this.listResumenDiarioTax = listResumenDiarioTax;
    }

    @Override
    public String toString() {
        return "ResumenDiario{" + "id=" + id + ", empresa=" + empresa + ", fechaEmision=" + fechaEmision + ", fechaResumen=" + fechaResumen + ", nombreArchivo=" + nombreArchivo + ", customizationId=" + customizationId + ", indSituacion=" + indSituacion + ", fechaEnvio=" + fechaEnvio + ", FechaGenXml=" + FechaGenXml + ", observacionEnvio=" + observacionEnvio + ", ticketOperacion=" + ticketOperacion + ", ticketSunat=" + ticketSunat + ", xmlHash=" + xmlHash + ", listResumenDiarioDet=" + listResumenDiarioDet + '}';
    }

    
}

