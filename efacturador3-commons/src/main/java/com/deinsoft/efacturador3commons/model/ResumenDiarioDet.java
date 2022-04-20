/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

/**
 *
 * @author EDWARD-PC
 */
@Entity(name = "resumenDiarioDet")
@Table(name = "resumen_diario_det")
public class ResumenDiarioDet {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "resumen_diario_det_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Valid
    @OneToOne
    @JoinColumn(name = "resumen_diario_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private ResumenDiario resumenDiario;
    
    @Column(name = "tip_doc_resumen")
    private String tipDocResumen;
    
    @Column(name = "nro_documento")
    private String nroDocumento;
    
    @Column(name = "tip_doc_usuario")
    private String tipDocUsuario;
    
    @Column(name = "num_doc_usuario")
    private String numDocUsuario;
    
    @Column(name = "moneda")
    private String moneda;
    
    @ColumnDefault("0")
    @Column(name = "total_val_grabado")
    private BigDecimal totValGrabado;
    
    @ColumnDefault("0")
    @Column(name = "total_val_exonerado")
    private BigDecimal totValExonerado;
    
    @ColumnDefault("0")
    @Column(name = "total_val_inafecto")
    private BigDecimal totValInafecto;
    
    @ColumnDefault("0")
    @Column(name = "total_val_exportado")
    private BigDecimal totValExportado;
    
    @ColumnDefault("0")
    @Column(name = "total_val_gratuito")
    private BigDecimal totValGratuito;
    
    @ColumnDefault("0")
    @Column(name = "total_otro_cargo")
    private BigDecimal totOtroCargo;
    
    @ColumnDefault("0")
    @Column(name = "total_imp_cpe")
    private BigDecimal totImpCpe;
    
    @Column(name = "tip_doc_modifico")
    private String tipDocModifico;
    
    @Column(name = "ser_doc_modifico")
    private String serDocModifico;
    
    @Column(name = "num_doc_modifico")
    private String numDocModifico;
    
    @Column(name = "tip_reg_percepcion")
    private String tipRegPercepcion;
    
    @Column(name = "por_percepcion")
    private String porPercepcion;
    
    @Column(name = "nom_base_percepcion")
    private BigDecimal monBasePercepcion;
    
    @Column(name = "nom_percepcion")
    private BigDecimal monPercepcion;
    
    @Column(name = "nom_tot_inc_percepcion")
    private BigDecimal monTotIncPercepcion;
    
    @Column(name = "condicion")
    private String condicion;
    
    public ResumenDiarioDet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipDocResumen() {
        return tipDocResumen;
    }

    public void setTipDocResumen(String tipDocResumen) {
        this.tipDocResumen = tipDocResumen;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getTipDocUsuario() {
        return tipDocUsuario;
    }

    public void setTipDocUsuario(String tipDocUsuario) {
        this.tipDocUsuario = tipDocUsuario;
    }

    public String getNumDocUsuario() {
        return numDocUsuario;
    }

    public void setNumDocUsuario(String numDocUsuario) {
        this.numDocUsuario = numDocUsuario;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getTotValGrabado() {
        return totValGrabado;
    }

    public void setTotValGrabado(BigDecimal totValGrabado) {
        this.totValGrabado = totValGrabado;
    }

    public BigDecimal getTotValExonerado() {
        return totValExonerado;
    }

    public void setTotValExonerado(BigDecimal totValExonerado) {
        this.totValExonerado = totValExonerado;
    }


    public BigDecimal getTotValInafecto() {
        return totValInafecto;
    }

    public void setTotValInafecto(BigDecimal totValInafecto) {
        this.totValInafecto = totValInafecto;
    }

    public BigDecimal getTotValExportado() {
        return totValExportado;
    }

    public void setTotValExportado(BigDecimal totValExportado) {
        this.totValExportado = totValExportado;
    }

    public BigDecimal getTotValGratuito() {
        return totValGratuito;
    }

    public void setTotValGratuito(BigDecimal totValGratuito) {
        this.totValGratuito = totValGratuito;
    }

    public BigDecimal getTotOtroCargo() {
        return totOtroCargo;
    }

    public void setTotOtroCargo(BigDecimal totOtroCargo) {
        this.totOtroCargo = totOtroCargo;
    }

    public BigDecimal getTotImpCpe() {
        return totImpCpe;
    }

    public void setTotImpCpe(BigDecimal totImpCpe) {
        this.totImpCpe = totImpCpe;
    }

    public String getTipDocModifico() {
        return tipDocModifico;
    }

    public void setTipDocModifico(String tipDocModifico) {
        this.tipDocModifico = tipDocModifico;
    }

    public String getSerDocModifico() {
        return serDocModifico;
    }

    public void setSerDocModifico(String serDocModifico) {
        this.serDocModifico = serDocModifico;
    }

    public String getNumDocModifico() {
        return numDocModifico;
    }

    public void setNumDocModifico(String numDocModifico) {
        this.numDocModifico = numDocModifico;
    }

    public String getTipRegPercepcion() {
        return tipRegPercepcion;
    }

    public void setTipRegPercepcion(String tipRegPercepcion) {
        this.tipRegPercepcion = tipRegPercepcion;
    }

    public String getPorPercepcion() {
        return porPercepcion;
    }

    public void setPorPercepcion(String porPercepcion) {
        this.porPercepcion = porPercepcion;
    }

    public BigDecimal getMonBasePercepcion() {
        return monBasePercepcion;
    }

    public void setMonBasePercepcion(BigDecimal monBasePercepcion) {
        this.monBasePercepcion = monBasePercepcion;
    }

    public BigDecimal getMonPercepcion() {
        return monPercepcion;
    }

    public void setMonPercepcion(BigDecimal monPercepcion) {
        this.monPercepcion = monPercepcion;
    }

    public BigDecimal getMonTotIncPercepcion() {
        return monTotIncPercepcion;
    }

    public void setMonTotIncPercepcion(BigDecimal monTotIncPercepcion) {
        this.monTotIncPercepcion = monTotIncPercepcion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public ResumenDiario getResumenDiario() {
        return resumenDiario;
    }

    public void setResumenDiario(ResumenDiario resumenDiario) {
        this.resumenDiario = resumenDiario;
    }

    @Override
    public String toString() {
        return "ResumenDiarioDet{" + "id=" + id + ", resumenDiario=" + resumenDiario + ", tipDocResumen=" + tipDocResumen + ", nroDocumento=" + nroDocumento + ", tipDocUsuario=" + tipDocUsuario + ", numDocUsuario=" + numDocUsuario + ", moneda=" + moneda + ", totValGrabado=" + totValGrabado + ", totValExoneado=" + totValExonerado + ", totValInafecto=" + totValInafecto + ", totValExportado=" + totValExportado + ", totValGratuito=" + totValGratuito + ", totOtroCargo=" + totOtroCargo + ", totImpCpe=" + totImpCpe + ", tipDocModifico=" + tipDocModifico + ", serDocModifico=" + serDocModifico + ", numDocModifico=" + numDocModifico + ", tipRegPercepcion=" + tipRegPercepcion + ", porPercepcion=" + porPercepcion + ", monBasePercepcion=" + monBasePercepcion + ", monPercepcion=" + monPercepcion + ", monTotIncPercepcion=" + monTotIncPercepcion + ", condicion=" + condicion + '}';
    }

    
    
}

