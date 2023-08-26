package com.app.fa.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Seller {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sellerid;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String phonenumber;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private boolean isactive;
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	
	
	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Seller(int sellerid, String name, String email,String phonenumber, String password, boolean isactive) {
		this();
		this.sellerid = sellerid;
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
		this.isactive = isactive;
	}
	@Override
	public String toString() {
		return "Seller [sellerid=" + sellerid + ", name=" + name + ", email=" + email + ", phonenumber=" + phonenumber
				+ ", password=" + password + ", isactive=" + isactive + "]";
	}
	
	
}
