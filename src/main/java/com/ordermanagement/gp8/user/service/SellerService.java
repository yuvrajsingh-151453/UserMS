package com.ordermanagement.gp8.user.service;

import java.util.List;
import com.ordermanagement.gp8.user.dto.SellerDTO;
import com.ordermanagement.gp8.user.exception.UserException;

public interface SellerService {
	
	public String SellerRegister(SellerDTO sellerDTO) throws UserException;
	public String SellerLogin(String email, String password) throws UserException;
	public String DeleteSeller(String sellerId);
	public SellerDTO getSellerBysellerId(String sellerId) throws UserException;
	public List<SellerDTO> getAllSellers() throws UserException;
	
	
}


