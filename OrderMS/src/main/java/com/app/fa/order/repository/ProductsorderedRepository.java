package com.app.fa.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.fa.order.entity.Productsordered;
import com.app.fa.order.entity.ProductsorderedId;


public interface ProductsorderedRepository extends JpaRepository<Productsordered,ProductsorderedId>{
	List<Productsordered> findByBuyerid(Integer buyerid);
}
