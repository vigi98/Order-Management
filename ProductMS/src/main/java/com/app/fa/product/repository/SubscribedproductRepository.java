package com.app.fa.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.fa.product.dto.SubscribedproductDTO;
import com.app.fa.product.entity.Subscribedproduct;
import com.app.fa.product.entity.SubscribedproductId;

public interface SubscribedproductRepository extends JpaRepository<Subscribedproduct,SubscribedproductId> {
	
	public SubscribedproductDTO findByBuyeridAndProdid(Integer buyerid,Integer prodid);

}
