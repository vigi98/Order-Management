package com.app.fa.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Buyer {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int buyerid;
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
	@Column(nullable = false)
	private boolean isprivileged;
	@Column(nullable = false)
	private int rewardpoints;
	
	public int getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
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
	public boolean isIsprivileged() {
		return isprivileged;
	}
	public void setIsprivileged(boolean isprivileged) {
		this.isprivileged = isprivileged;
	}
	public int getRewardpoints() {
		return rewardpoints;
	}
	public void setRewardpoints(int rewardpoints) {
		this.rewardpoints = rewardpoints;
	}
	
	public Buyer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Buyer(int buyerid, String name, String email, String phonenumber, String password, boolean isactive,
			boolean isprivileged, int rewardpoints) {
		this();
		this.buyerid = buyerid;
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
		this.isactive = isactive;
		this.isprivileged = isprivileged;
		this.rewardpoints = rewardpoints;
	}
	@Override
	public String toString() {
		return "Buyer [buyerid=" + buyerid + ", name=" + name + ", email=" + email + ", phonenumber=" + phonenumber
				+ ", password=" + password + ", isactive=" + isactive + ", isprivileged=" + isprivileged
				+ ", rewardpoints=" + rewardpoints + "]";
	}
	
	

}
