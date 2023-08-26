package com.app.fa.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.fa.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
  List<Order>findByBuyerid(int buyerid);
	
}
