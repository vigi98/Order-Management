package com.app.fa.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ProductsorderedId.class)
public class Productsordered {


	@Id
	@Column(nullable = false)
	private int buyerid ;
	
	@Id
	@Column(nullable = false)
	private int prodid ;
	
	@Column(nullable = false)
	private int sellerid ;
	@Column(nullable = false)
	private int quantity ;
	
	
	public Productsordered() {
		super();
	}


	public Productsordered(int buyerid, int prodid, int sellerid, int quantity) {
		this();
		this.buyerid = buyerid;
		this.prodid = prodid;
		this.sellerid = sellerid;
		this.quantity = quantity;
	}


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


	public int getSellerid() {
		return sellerid;
	}


	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "Productsordered [buyerid=" + buyerid + ", prodid=" + prodid + ", sellerid=" + sellerid + ", quantity="
				+ quantity + "]";
	}
	
	
	
}
