package com.deinsoft.efacturador3commons.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import java.util.Date;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Entity(name="secUser")
@Table(name="sec_user")
public class SecUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sec_user_id",nullable = false, unique = true)
	private long id;
	
	@NotEmpty
	@Size(max = 255)
	@Column(name="name", length = 255, nullable = false)
	private String name;
	
	@NotEmpty
	@Size(max = 255)
	@Column(name="email", length = 255, nullable = false)
	private String email;
	
	@NotEmpty
	@Size(max = 255)
	@Column(name="password", length = 255, nullable = true)
	private String password;
	
//	@OneToMany(fetch=FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinTable(name="sec_role_user",
//	        joinColumns = {@JoinColumn(name="sec_user_id", referencedColumnName="sec_user_id")},
//	        inverseJoinColumns = {@JoinColumn(name="sec_role_id", referencedColumnName="sec_role_id")}
//    )
//    private List<SecRoleUser> listSecRoleUser;
	
	@OneToMany(mappedBy = "secUser", orphanRemoval = true,fetch=FetchType.LAZY)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "secUser" }, allowSetters = true)
    private Set<SecRoleUser> listSecRoleUser ;
	
	@Column(name = "isactive")
	private int state;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<SecRoleUser> getListSecRoleUser() {
		return listSecRoleUser;
	}
	public void setListSecRoleUser(Set<SecRoleUser> listSecRoleUser) {
		this.listSecRoleUser = listSecRoleUser;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
//    @Override
//	public String toString() {
//		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password +"]";	
//		}

	
	
	
}
