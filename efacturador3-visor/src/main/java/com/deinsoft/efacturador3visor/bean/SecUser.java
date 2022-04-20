package com.deinsoft.efacturador3visor.bean;

import java.io.Serializable;

public class SecUser implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
//	@OneToMany(mappedBy = "secUser", orphanRemoval = true,fetch=FetchType.LAZY)
//    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//    @JsonIgnoreProperties(value = { "secUser" }, allowSetters = true)
//    private Set<SecRoleUser> listSecRoleUser ;
//	
//	@Column(name = "isactive")
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
////	public Set<SecRoleUser> getListSecRoleUser() {
////		return listSecRoleUser;
////	}
////	public void setListSecRoleUser(Set<SecRoleUser> listSecRoleUser) {
////		this.listSecRoleUser = listSecRoleUser;
////	}
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
