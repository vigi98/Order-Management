package com.app.fa.user.dto;

import javax.validation.constraints.Min;

import com.app.fa.user.entity.Cart;

public class CartDTO {
	@Min(value=1)
	private int buyerid;
	@Min(value=1)
	private int prodid;
	@Min(value=1)
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
	
	//Entity to DTO
	public static CartDTO valueOf(Cart cart) {
		
		CartDTO cdto= new CartDTO();
		cdto.setBuyerid(cart.getBuyerid());
		cdto.setProdid(cart.getProdid());
		cdto.setQuantity(cart.getQuantity());
		return cdto;
	}
	
	//DTO to Entity
	public Cart createEntity() {
		Cart cart= new Cart();
		cart.setBuyerid(this.getBuyerid());
		cart.setProdid(this.getProdid());
		cart.setQuantity(this.getQuantity());
		return cart;
	}

	@Override
	public String toString() {
		return "CartDTO [buyerid=" + buyerid + ", prodid=" + prodid + ", quantity=" + quantity + "]";
	}
	
	

}
