package com.deinsoft.efacturador3commons.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity(name = "PARAMETRO")
public class Parametro {

    @Id
    private String id_para;
    private String cod_para;
    private String nom_para;

    public void setId_para(String id_para) {
        this.id_para = id_para;
    }
    private String tip_para;
    private String val_para;
    private String ind_esta_para;

    public void setCod_para(String cod_para) {
        this.cod_para = cod_para;
    }

    public void setNom_para(String nom_para) {
        this.nom_para = nom_para;
    }

    public void setTip_para(String tip_para) {
        this.tip_para = tip_para;
    }

    public void setVal_para(String val_para) {
        this.val_para = val_para;
    }

    public void setInd_esta_para(String ind_esta_para) {
        this.ind_esta_para = ind_esta_para;
    }

    public String getId_para() {
        return this.id_para;
    }

    public String getCod_para() {
        return this.cod_para;
    }

    public String getNom_para() {
        return this.nom_para;
    }

    public String getTip_para() {
        return this.tip_para;
    }

    public String getVal_para() {
        return this.val_para;
    }

    public String getInd_esta_para() {
        return this.ind_esta_para;
    }
}
