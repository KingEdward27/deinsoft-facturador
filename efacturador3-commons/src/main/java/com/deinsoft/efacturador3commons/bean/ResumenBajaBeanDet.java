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
public class ResumenBajaBeanDet {
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank
    private String tipoDocumentoBaja;
    
    @NotBlank
    private String nroDocumentoBaja;
    
    @NotBlank
    private String motivoBajaDocumento;
    
    public ResumenBajaBeanDet() {
    }
    public String getTipoDocumentoBaja() {
        return tipoDocumentoBaja;
    }

    public void setTipoDocumentoBaja(String tipoDocumentoBaja) {
        this.tipoDocumentoBaja = tipoDocumentoBaja;
    }

    public String getNroDocumentoBaja() {
        return nroDocumentoBaja;
    }

    public void setNroDocumentoBaja(String nroDocumentoBaja) {
        this.nroDocumentoBaja = nroDocumentoBaja;
    }

    public String getMotivoBajaDocumento() {
        return motivoBajaDocumento;
    }

    public void setMotivoBajaDocumento(String motivoBajaDocumento) {
        this.motivoBajaDocumento = motivoBajaDocumento;
    }

    @Override
    public String toString() {
        return "ResumenBajas{" + " tipoDocumentoBaja=" + tipoDocumentoBaja +  ", nroDocumentoBaja=" + nroDocumentoBaja + ", motivoBajaDocumento=" + motivoBajaDocumento + '}';
    }
    
    
}

