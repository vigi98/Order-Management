package com.app.fa.order.dto;

import javax.validation.constraints.Min;

import com.app.fa.order.entity.Productsordered;

public class ProductsorderedDTO {
	
	@Min(value=1,message="buyerid required")
	private int buyerid ;
	@Min(value=1,message="prodid required")
	private int prodid ;
	@Min(value=1,message="sellerid required")
	private int sellerid ;
	@Min(value=1,message="quantity required")
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
	
	//Converts Entity to DTO
		public static ProductsorderedDTO valueOf(Productsordered po) {
			ProductsorderedDTO pdto = new ProductsorderedDTO();
			pdto.setProdid(po.getProdid());
			pdto.setBuyerid(po.getBuyerid());
			pdto.setSellerid(po.getSellerid());
			pdto.setQuantity(po.getQuantity());
			return pdto;
		}
		
		//Converts DTO to Entity
		public Productsordered createEntity() {
			Productsordered po= new Productsordered();
			po.setBuyerid(this.getBuyerid());
		    po.setProdid(this.getProdid());
		    po.setQuantity(this.getQuantity());
		    po.setSellerid(this.getSellerid());
			return po;
		}

	@Override
	public String toString() {
		return "ProductsorderedDTO [buyerid=" + buyerid + ", prodid=" + prodid + ", sellerid=" + sellerid
				+ ", quantity=" + quantity + "]";
	}
	
	

}
