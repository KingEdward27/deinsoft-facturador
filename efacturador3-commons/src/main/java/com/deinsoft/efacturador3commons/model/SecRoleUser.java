package com.deinsoft.efacturador3commons.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "secRoleUser")
@Table(name = "sec_role_user")
public class SecRoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sec_role_user_id", nullable = false, unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sec_user_id")
    private SecUser secUser;

    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn(name = "sec_role_id")
    private SecRole secRole;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SecUser getSecUser() {
        return secUser;
    }

    public void setSecUser(SecUser secUser) {
        this.secUser = secUser;
    }

    public SecRole getSecRole() {
        return secRole;
    }

    public void setSecRole(SecRole secRole) {
        this.secRole = secRole;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }



}
