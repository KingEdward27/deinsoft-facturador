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
public class ResumenBajaBean {
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank
    private String fechaDocumentoBaja;

    @NotBlank
    private String fechaComunicacionBaja;
    
    
    public ResumenBajaBean() {
    }


    public String getFechaDocumentoBaja() {
        return fechaDocumentoBaja;
    }

    public void setFechaDocumentoBaja(String fechaDocumentoBaja) {
        this.fechaDocumentoBaja = fechaDocumentoBaja;
    }

    public String getFechaComunicacionBaja() {
        return fechaComunicacionBaja;
    }

    public void setFechaComunicacionBaja(String fechaComunicacionBaja) {
        this.fechaComunicacionBaja = fechaComunicacionBaja;
    }

    @Valid
    private Set<ResumenBajaBeanDet> lista_comprobantes;


    public Set<ResumenBajaBeanDet> getLista_comprobantes() {
        return lista_comprobantes;
    }

    public void setLista_comprobantes(Set<ResumenBajaBeanDet> lista_comprobantes) {
        this.lista_comprobantes = lista_comprobantes;
    }

    @Override
    public String toString() {
        return "ResumenBajaBean{" + "fechaDocumentoBaja=" + fechaDocumentoBaja + ", fechaComunicacionBaja=" + fechaComunicacionBaja + '}';
    }

    
    
}

