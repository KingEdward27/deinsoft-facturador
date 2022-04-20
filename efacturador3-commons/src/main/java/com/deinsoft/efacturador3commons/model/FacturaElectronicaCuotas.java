/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EDWARD-PC
 */
@Entity(name = "facturaElectronicaCuotas")
@Table(name = "factura_electronica_cuota")
public class FacturaElectronicaCuotas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "factura_electronica_cuota_id", nullable = false, unique = true)
    private long id;
    
    @OneToOne
    @JoinColumn(name="factura_electronica_id")
    private FacturaElectronica facturaElectronica;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto_cuota_pago")
    private BigDecimal mtoCuotaPago;
    
    @Column(name = "tipo_moneda_cuota_pago")
    private String tipMonedaCuotaPago;
    
    @Column(name = "fecha_cuota_pago")
    private Date fecCuotaPago;

    public FacturaElectronicaCuotas() {
    }

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

    public BigDecimal getMtoCuotaPago() {
        return mtoCuotaPago;
    }

    public void setMtoCuotaPago(BigDecimal mtoCuotaPago) {
        this.mtoCuotaPago = mtoCuotaPago;
    }

    public String getTipMonedaCuotaPago() {
        return tipMonedaCuotaPago;
    }

    public void setTipMonedaCuotaPago(String tipMonedaCuotaPago) {
        this.tipMonedaCuotaPago = tipMonedaCuotaPago;
    }

    public Date getFecCuotaPago() {
        return fecCuotaPago;
    }

    public void setFecCuotaPago(Date fecCuotaPago) {
        this.fecCuotaPago = fecCuotaPago;
    }
    
    
}
