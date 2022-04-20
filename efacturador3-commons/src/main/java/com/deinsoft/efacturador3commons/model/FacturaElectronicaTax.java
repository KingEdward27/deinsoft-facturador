/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author EDWARD-PC
 */
@Entity(name = "facturaElectronicaTax")
@Table(name = "factura_electronica_tax")
public class FacturaElectronicaTax {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "factura_electronica_tax_id", nullable = false, unique = true)
    private long id;
    
    @OneToOne
    @JoinColumn(name="factura_electronica_id")
    private FacturaElectronica facturaElectronica;
    
    @Column(name = "tax_id")
    private int taxId;
    
    @Column(name = "baseamt")
    private BigDecimal baseamt;
    
    @Column(name = "taxtotal")
    private BigDecimal taxtotal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FacturaElectronica getFacturaElectronica() {
        return facturaElectronica;
    }

    public void setFacturaElectronica(FacturaElectronica facturaElectronica) {
        this.facturaElectronica = facturaElectronica;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public BigDecimal getBaseamt() {
        return baseamt;
    }

    public void setBaseamt(BigDecimal baseamt) {
        this.baseamt = baseamt;
    }

    public BigDecimal getTaxtotal() {
        return taxtotal;
    }

    public void setTaxtotal(BigDecimal taxtotal) {
        this.taxtotal = taxtotal;
    }
    
    
}
