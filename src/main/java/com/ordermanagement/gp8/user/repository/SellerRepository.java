package com.ordermanagement.gp8.user.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.ordermanagement.gp8.user.entity.Seller;

public interface SellerRepository extends CrudRepository<Seller, String> {
	public Seller findByEmail(String email);
	public Seller findByPhoneNumber(String phoneNumber);
	public Seller findBySellerId(String sellerId);
	public List<Seller> findAll();

}
