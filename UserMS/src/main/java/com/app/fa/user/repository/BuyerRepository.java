package com.app.fa.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.fa.user.dto.BuyerDTO;
import com.app.fa.user.entity.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
	
	Buyer findByEmail(String email);
	Buyer findByPhonenumber(String phonenumber);
}
