package com.app.fa.user.dto;

import javax.validation.constraints.Min;

import com.app.fa.user.entity.Wishlist;

public class WishlistDTO {

	@Min(value=1)
	private int buyerid;
	@Min(value=1)
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
	
	// Converts Entity to DTO
				public static WishlistDTO valueOf(Wishlist wish) {
					WishlistDTO wdto = new WishlistDTO();
					wdto.setBuyerid(wish.getBuyerid());
					wdto.setProdid(wish.getProdid());
		    return wdto;
		}
				
	//Converts DTO to Entity
				public Wishlist createEntity() {
					Wishlist wish= new Wishlist();
					wish.setBuyerid(this.getBuyerid());
					wish.setProdid(this.getProdid());
					return wish;
				}
								
	@Override
	public String toString() {
		return "WishlistDTO [buyerid=" + buyerid + ", prodid=" + prodid + "]";
	}
		
}
