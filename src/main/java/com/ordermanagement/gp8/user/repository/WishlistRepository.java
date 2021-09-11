package com.ordermanagement.gp8.user.repository;

import org.springframework.data.repository.CrudRepository;



import com.ordermanagement.gp8.user.entity.Wishlist;
import com.ordermanagement.gp8.user.utility.Userpk;


public interface WishlistRepository extends CrudRepository<Wishlist, Userpk> {
	public Wishlist findByUserIdBuyerIdAndUserIdProdId(String buyerId,String ProdId);
	public void deleteByUserIdBuyerIdAndUserIdProdId(String buyerId,String prodId);

}