/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.bean;

import java.util.Date;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author EDWARD-PC
 */
public class ResumenDiarioBeanDet {
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank
    private String tipDocResumen;
    
    @NotBlank
    private String nroDocumento;
    
    @NotBlank
    private String tipDocUsuario;
    
    @NotBlank
    private String numDocUsuario;
    
    @NotBlank
    private String moneda;
    
    @NotBlank
    private String totValGrabado;
    
    @NotBlank
    private String totValExonerado;
    
    @NotBlank
    private String totValInafecto;
    
    @NotBlank
    private String totValExportado;
    
    @NotBlank
    private String totValGratuito;
    
    @NotBlank
    private String totOtroCargo;
    
    @NotBlank
    private String totImpCpe;
    
    private String tipDocModifico;
    
    private String serDocModifico;
    
    private String numDocModifico;
    
    private String tipRegPercepcion;
    
    private String porPercepcion;
    
    private String monBasePercepcion;
    
    private String monPercepcion;
    
    private String monTotIncPercepcion;
    
    @NotBlank
    private String condicion;
    
    public ResumenDiarioBeanDet() {
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

    public String getTotValGrabado() {
        return totValGrabado;
    }

    public void setTotValGrabado(String totValGrabado) {
        this.totValGrabado = totValGrabado;
    }

    public String getTotValExonerado() {
        return totValExonerado;
    }

    public void setTotValExonerado(String totValExonerado) {
        this.totValExonerado = totValExonerado;
    }

    public String getTotValInafecto() {
        return totValInafecto;
    }

    public void setTotValInafecto(String totValInafecto) {
        this.totValInafecto = totValInafecto;
    }

    public String getTotValExportado() {
        return totValExportado;
    }

    public void setTotValExportado(String totValExportado) {
        this.totValExportado = totValExportado;
    }

    public String getTotValGratuito() {
        return totValGratuito;
    }

    public void setTotValGratuito(String totValGratuito) {
        this.totValGratuito = totValGratuito;
    }

    public String getTotOtroCargo() {
        return totOtroCargo;
    }

    public void setTotOtroCargo(String totOtroCargo) {
        this.totOtroCargo = totOtroCargo;
    }

    public String getTotImpCpe() {
        return totImpCpe;
    }

    public void setTotImpCpe(String totImpCpe) {
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

    public String getMonBasePercepcion() {
        return monBasePercepcion;
    }

    public void setMonBasePercepcion(String monBasePercepcion) {
        this.monBasePercepcion = monBasePercepcion;
    }

    public String getMonPercepcion() {
        return monPercepcion;
    }

    public void setMonPercepcion(String monPercepcion) {
        this.monPercepcion = monPercepcion;
    }

    public String getMonTotIncPercepcion() {
        return monTotIncPercepcion;
    }

    public void setMonTotIncPercepcion(String monTotIncPercepcion) {
        this.monTotIncPercepcion = monTotIncPercepcion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
    
    
}

