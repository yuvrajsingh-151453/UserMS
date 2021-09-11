package com.ordermanagement.gp8.user.service;

import java.util.List;


import com.ordermanagement.gp8.user.dto.BuyerDTO;
import com.ordermanagement.gp8.user.dto.CartDTO;
import com.ordermanagement.gp8.user.exception.UserException;

public interface BuyerService {

	public String BuyerRegister(BuyerDTO buyerDTO) throws UserException;
	public String BuyerLogin(String email, String password) throws UserException;
	public String DeleteBuyer(String buyerId);
	public String AddtoCart(String prodId, String buyerId, Integer quantity);
	public List<CartDTO> getCartProducts(String id) throws UserException;
	public String RemoveFromCart(String buyerId, String prodId) throws UserException;
	public String AddtoWishlist(String prodId,String buyerId);
	public String UpdateRewPoints(String buyerId, Integer rewPoints) throws UserException;
	public String RemoveFromWishlist(String buyerId, String prodId) throws UserException;
	public BuyerDTO getBuyerBybuyerId(String buyerId) throws UserException;
	public List<BuyerDTO> getAllBuyers() throws UserException;
	
}
