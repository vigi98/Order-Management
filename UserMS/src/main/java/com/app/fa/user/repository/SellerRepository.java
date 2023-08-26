package com.app.fa.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.fa.user.entity.Seller;


public interface SellerRepository extends JpaRepository<Seller,Integer> {
	Seller findByEmail(String email);
	Seller findByPhonenumber(String phonenumber);
}
