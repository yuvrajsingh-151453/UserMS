package com.ordermanagement.gp8.user.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;


import com.ordermanagement.gp8.user.entity.Buyer;


public interface BuyerRepository extends CrudRepository<Buyer, String> {
	public Buyer findByBuyerId(String buyerId);
	public Buyer findByEmail(String email);
	public Buyer findByPhoneNumber(String phoneNumber);
	public List<Buyer> findAll();

	
}
