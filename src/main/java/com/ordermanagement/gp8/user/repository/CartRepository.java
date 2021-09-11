package com.ordermanagement.gp8.user.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.ordermanagement.gp8.user.entity.Cart;
import com.ordermanagement.gp8.user.utility.Userpk;

public interface CartRepository extends CrudRepository<Cart, Userpk> {
	public Cart findByUserpkBuyerIdAndUserpkProdId(String buyerId,String ProdId);
	public void deleteByUserpkBuyerIdAndUserpkProdId(String buyerId,String prodId);
	public List<Cart> findByUserpkBuyerId(String buyerId); 
	
}
