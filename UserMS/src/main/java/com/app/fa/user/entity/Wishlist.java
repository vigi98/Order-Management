package com.app.fa.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(WishlistId.class)
public class Wishlist {

	@Id
	@Column(nullable = false)
	private int buyerid;
	@Id
	@Column(nullable = false)
	private int prodid;
	
	public int getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	
	
	public Wishlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Wishlist(int buyerid, int prodid) {
		this();
		this.buyerid = buyerid;
		this.prodid = prodid;
	}
	@Override
	public String toString() {
		return "Wishlist [buyerid=" + buyerid + ", prodid=" + prodid + "]";
	}
	
	
}
