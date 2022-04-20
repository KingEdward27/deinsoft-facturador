/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.bean;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author EDWARD-PC
 */
public class ResumenDiarioBeanTax {
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank
    private String idLineaRd;
    
    @NotBlank
    private String ideTributoRd;
    
    @NotBlank
    private String nomTributoRd;
    
    @NotBlank
    private String codTipTributoRd;
    
    @NotBlank
    private String mtoBaseImponibleRd;
    
    @NotBlank
    private String mtoTributoRd;
    
    public ResumenDiarioBeanTax() {
    }

    public String getIdLineaRd() {
        return idLineaRd;
    }

    public void setIdLineaRd(String idLineaRd) {
        this.idLineaRd = idLineaRd;
    }

    public String getIdeTributoRd() {
        return ideTributoRd;
    }

    public void setIdeTributoRd(String ideTributoRd) {
        this.ideTributoRd = ideTributoRd;
    }

    public String getNomTributoRd() {
        return nomTributoRd;
    }

    public void setNomTributoRd(String nomTributoRd) {
        this.nomTributoRd = nomTributoRd;
    }

    public String getCodTipTributoRd() {
        return codTipTributoRd;
    }

    public void setCodTipTributoRd(String codTipTributoRd) {
        this.codTipTributoRd = codTipTributoRd;
    }

    public String getMtoBaseImponibleRd() {
        return mtoBaseImponibleRd;
    }

    public void setMtoBaseImponibleRd(String mtoBaseImponibleRd) {
        this.mtoBaseImponibleRd = mtoBaseImponibleRd;
    }

    public String getMtoTributoRd() {
        return mtoTributoRd;
    }

    public void setMtoTributoRd(String mtoTributoRd) {
        this.mtoTributoRd = mtoTributoRd;
    }

    
}

