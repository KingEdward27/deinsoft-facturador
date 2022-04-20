/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EDWARD-PC
 */
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idempresa")
    private Integer id;
    
    @Size(max = 300)
    @Column(name = "razon_social")
    private String razonSocial;
    
    @Size(max = 300)
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    
    @Size(max = 300)
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "tipodoc")
    private Integer tipodoc;
    
    @Size(max = 13)
    @Column(name = "numdoc")
    private String numdoc;
    
    @Size(max = 4)
    @Column(name = "serie")
    private String serie;
    
    @Column(name = "usuariosol")
    private String usuariosol;
    
    @Column(name = "clavesol")
    private String clavesol;
    
    @Column(name = "cert_name")
    private String certName;
    
    @Column(name = "cert_pass")
    private String certPass;
    
    @Column(name = "token")
    @Size(max = 1000)
    private String token;
    
    @Column(name = "estado")
    private Character estado;

    public Empresa() {
    }

    public Empresa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Integer getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(Integer tipodoc) {
        this.tipodoc = tipodoc;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getUsuariosol() {
        return usuariosol;
    }

    public void setUsuariosol(String usuariosol) {
        this.usuariosol = usuariosol;
    }

    public String getClavesol() {
        return clavesol;
    }

    public void setClavesol(String clavesol) {
        this.clavesol = clavesol;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertPass() {
        return certPass;
    }

    public void setCertPass(String certPass) {
        this.certPass = certPass;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", razonSocial=" + razonSocial + ", tipodoc=" + tipodoc + ", numdoc=" + numdoc + ", serie=" + serie + ", usuariosol=" + usuariosol + ", clavesol=" + clavesol + ", certName=" + certName + ", certPass=" + certPass + ", token=" + token + ", estado=" + estado + '}';
    }
    
    
}
