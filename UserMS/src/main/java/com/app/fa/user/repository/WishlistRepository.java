package com.app.fa.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.fa.user.entity.Wishlist;
import com.app.fa.user.entity.WishlistId;

public interface WishlistRepository extends JpaRepository<Wishlist,WishlistId> {
	Wishlist findByBuyeridAndProdid(int buyerid,int prodid);
	void deleteByBuyerid(int buyerid);
	List<Wishlist> findByBuyerid(int buyerid);
}
