package com.app.fa.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.fa.user.entity.Cart;
import com.app.fa.user.entity.CartId;

public interface CartRepository extends JpaRepository<Cart,CartId>{

	Cart findByBuyeridAndProdid(int buyerid,int prodid);
	void deleteByBuyerid(int buyerid);
    
	List<Cart> findByBuyerid(int buyerid);
}
