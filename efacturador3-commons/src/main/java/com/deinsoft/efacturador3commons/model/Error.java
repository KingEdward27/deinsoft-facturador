package com.deinsoft.efacturador3commons.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity(name = "ERROR")
public class Error {
    @Id
    private String cod_cataerro;
    private String nom_cataerro;
    private String ind_estado;

    public void setCod_cataerro(String cod_cataerro) {
        this.cod_cataerro = cod_cataerro;
    }

    public void setNom_cataerro(String nom_cataerro) {
        this.nom_cataerro = nom_cataerro;
    }

    public void setInd_estado(String ind_estado) {
        this.ind_estado = ind_estado;
    }

    public String getCod_cataerro() {
        return this.cod_cataerro;
    }

    public String getNom_cataerro() {
        return this.nom_cataerro;
    }

    public String getInd_estado() {
        return this.ind_estado;
    }
}
