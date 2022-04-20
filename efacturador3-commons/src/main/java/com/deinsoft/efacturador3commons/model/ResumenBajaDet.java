/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Entity(name = "resumenBajaDet")
@Table(name = "resumen_baja_det")
public class ResumenBajaDet {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "resumen_baja_det_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Valid
    @OneToOne
    @JoinColumn(name = "resumen_baja_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private ResumenBaja resumenBaja;
    
    @Column(name = "tipo_documento_baja")
    private String tipoDocumentoBaja;
    
    @Column(name = "serie_documento_baja")
    private String serieDocumentoBaja;
    
    @Column(name = "nro_documento_baja")
    private String nroDocumentoBaja;
    
    @Column(name = "motivo_baja_documento")
    private String motivoBajaDocumento;
    
    public ResumenBajaDet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoDocumentoBaja() {
        return tipoDocumentoBaja;
    }

    public void setTipoDocumentoBaja(String tipoDocumentoBaja) {
        this.tipoDocumentoBaja = tipoDocumentoBaja;
    }

    public String getSerieDocumentoBaja() {
        return serieDocumentoBaja;
    }

    public void setSerieDocumentoBaja(String serieDocumentoBaja) {
        this.serieDocumentoBaja = serieDocumentoBaja;
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

    public ResumenBaja getResumenBaja() {
        return resumenBaja;
    }

    public void setResumenBaja(ResumenBaja resumenBaja) {
        this.resumenBaja = resumenBaja;
    }

    @Override
    public String toString() {
        return "ResumenBajas{" + "id=" + id + ", tipoDocumentoBaja=" + tipoDocumentoBaja + ", serieDocumentoBaja=" + serieDocumentoBaja + ", nroDocumentoBaja=" + nroDocumentoBaja + ", motivoBajaDocumento=" + motivoBajaDocumento + '}';
    }
    
    
}

