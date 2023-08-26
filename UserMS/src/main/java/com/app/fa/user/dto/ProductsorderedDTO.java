package com.app.fa.user.dto;

public class ProductsorderedDTO {
	
	
	private int buyerid ;
	private int prodid ;
	private int sellerid ;
	private int quantity ;

	public ProductsorderedDTO() {
		super();
	}

	public ProductsorderedDTO(int buyerid, int prodid, int sellerid, int quantity) {
		super();
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
		return "ProductsorderedDTO [buyerid=" + buyerid + ", prodid=" + prodid + ", sellerid=" + sellerid
				+ ", quantity=" + quantity + "]";
	}
	
	

}
