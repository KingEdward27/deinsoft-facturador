/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.bean;

import java.util.Date;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author EDWARD-PC
 */
public class ResumenDiarioBean {
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank
    private String fechaEmision;

    @NotBlank
    private String fechaResumen;
    
    
    public ResumenDiarioBean() {
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaResumen() {
        return fechaResumen;
    }

    public void setFechaResumen(String fechaResumen) {
        this.fechaResumen = fechaResumen;
    }

    @Valid
    private Set<ResumenDiarioBeanDet> lista_comprobantes;

    @Valid
    private Set<ResumenDiarioBeanTax> lista_tributos;

    public Set<ResumenDiarioBeanDet> getLista_comprobantes() {
        return lista_comprobantes;
    }

    public void setLista_comprobantes(Set<ResumenDiarioBeanDet> lista_comprobantes) {
        this.lista_comprobantes = lista_comprobantes;
    }
    

    public Set<ResumenDiarioBeanTax> getLista_tributos() {
        return lista_tributos;
    }

    public void setLista_tributos(Set<ResumenDiarioBeanTax> lista_tributos) {
        this.lista_tributos = lista_tributos;
    }

    @Override
    public String toString() {
        return "ResumenDiarioBean{" + "fechaEmision=" + fechaEmision + ", fechaResumen=" + fechaResumen + ", lista_comprobantes=" + lista_comprobantes + ", lista_tributos=" + lista_tributos + '}';
    }
    
    
    
    
    
}

