package com.app.fa.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(CartId.class)
public class Cart {

	@Id
	@Column(nullable = false)
	private int buyerid;
	@Id
	@Column(nullable = false)
	private int prodid;
	
	@Column(nullable = false)
	private int quantity;

	
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


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Cart(int buyerid, int prodid, int quantity) {
		this();
		this.buyerid = buyerid;
		this.prodid = prodid;
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "Cart [buyerid=" + buyerid + ", prodid=" + prodid + ", quantity=" + quantity + "]";
	} 
	
	
}
