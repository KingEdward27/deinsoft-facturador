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

/**
 *
 * @author EDWARD-PC
 */
@Entity(name = "resumenDiarioTax")
@Table(name = "resumen_diario_tax")
public class ResumenDiarioTax {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "resumen_diario_tax_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Valid
    @OneToOne
    @JoinColumn(name = "resumen_diario_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private ResumenDiario resumenDiario;
    
    @Column(name = "id_lineard")
    private int idLineaRd;
    
    @Column(name = "ide_tributo_rd")
    private String ideTributoRd;
    
    @Column(name = "nom_tributo_rd")
    private String nomTributoRd;
    
    @Column(name = "cod_tip_tributo_rd")
    private String codTipTributoRd;
    
    @Column(name = "mto_base_imponible_rd")
    private BigDecimal mtoBaseImponibleRd;
    
    @Column(name = "mto_tributo_rd")
    private BigDecimal mtoTributoRd;
    
    public ResumenDiarioTax() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ResumenDiario getResumenDiario() {
        return resumenDiario;
    }

    public void setResumenDiario(ResumenDiario resumenDiario) {
        this.resumenDiario = resumenDiario;
    }

    public int getIdLineaRd() {
        return idLineaRd;
    }

    public void setIdLineaRd(int idLineaRd) {
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

    public BigDecimal getMtoBaseImponibleRd() {
        return mtoBaseImponibleRd;
    }

    public void setMtoBaseImponibleRd(BigDecimal mtoBaseImponibleRd) {
        this.mtoBaseImponibleRd = mtoBaseImponibleRd;
    }

    public BigDecimal getMtoTributoRd() {
        return mtoTributoRd;
    }

    public void setMtoTributoRd(BigDecimal mtoTributoRd) {
        this.mtoTributoRd = mtoTributoRd;
    }


    @Override
    public String toString() {
        return "ResumenDiarioTax{" + "id=" + id + ", resumenDiario=" + resumenDiario + ", idLineaRd=" + idLineaRd + ", ideTributoRd=" + ideTributoRd + ", nomTributoRd=" + nomTributoRd + ", codTipTributoRd=" + codTipTributoRd + ", mtoBaseImponibleRd=" + mtoBaseImponibleRd + ", mtoTributoRd=" + mtoTributoRd + '}';
    }


    
    
}

