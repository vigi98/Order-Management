package com.app.fa.order.entity;

import java.io.Serializable;

public class ProductsorderedId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int buyerid ;
	private int prodid ;
	
	public ProductsorderedId() {
		super();
	}
	
	public ProductsorderedId(int buyerid, int prodid) {
		this();
		this.buyerid = buyerid;
		this.prodid = prodid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buyerid;
		result = prime * result + prodid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductsorderedId other = (ProductsorderedId) obj;
		if (buyerid != other.buyerid)
			return false;
		if (prodid != other.prodid)
			return false;
		return true;
	}
	

}
