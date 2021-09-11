package com.ordermanagement.gp8.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ordermanagement.gp8.user.dto.SellerDTO;
import com.ordermanagement.gp8.user.entity.Seller;
import com.ordermanagement.gp8.user.exception.UserException;
import com.ordermanagement.gp8.user.repository.SellerRepository;
import com.ordermanagement.gp8.user.validator.UserValidator;

@Service(value = "sellerService")
@Transactional
public class SellerServiceImpl implements SellerService {
	
		
@Autowired
 SellerRepository sellerRepo;
		
//seller register
@Override
		public String SellerRegister(SellerDTO sellerDTO) throws UserException {
			Seller seller = sellerRepo.findByPhoneNumber(sellerDTO.getPhoneNumber());
			if(seller != null)
	        throw new UserException("Seller Already exist");
			seller = new Seller();
			seller.setSellerId(sellerDTO.getSellerId());
			seller.setName(sellerDTO.getName());
			seller.setEmail(sellerDTO.getEmail());
			seller.setPhoneNumber(sellerDTO.getPhoneNumber());
			seller.setPassword(sellerDTO.getPassword());
			seller.setIsActive("Yes");
			sellerRepo.save(seller);
			return "Seller successfully registetred with seller Id : "+seller.getSellerId();
		}

//get by seller id
@Override
public SellerDTO getSellerBysellerId(String sellerId) throws UserException {
	Seller seller1 = sellerRepo.findBySellerId(sellerId);
	if(seller1 == null)
	throw new UserException("Seller not available");
	SellerDTO sellerDTO = new SellerDTO();
	sellerDTO.setSellerId(seller1.getSellerId());
	sellerDTO.setName(seller1.getName());
	sellerDTO.setEmail(seller1.getEmail());
	sellerDTO.setPhoneNumber(seller1.getPhoneNumber());
	sellerDTO.setPassword(seller1.getPassword());
	sellerDTO.setIsActive(seller1.getIsActive());
	
	return sellerDTO;
}


//get all seller
 @Override
   public List<SellerDTO> getAllSellers() throws UserException {
	List<Seller> list = sellerRepo.findAll();
	if(list.isEmpty())
	throw new UserException("Sellerlist is empty");
	List<SellerDTO> list1 = new ArrayList<>();
	list.forEach(lists -> {
		SellerDTO s1= new SellerDTO();
		s1.setSellerId(lists.getSellerId());
		s1.setName(lists.getName());
		s1.setEmail(lists.getEmail());
		s1.setPhoneNumber(lists.getPhoneNumber());
		s1.setPassword(lists.getPassword());
		s1.setIsActive(lists.getIsActive());
		list1.add(s1);
	}
	);
	return list1;
}
 
 
//login as seller
	@Override
		public String SellerLogin(String email, String password) throws UserException {
	        if(!UserValidator.validateEmail(email))
			throw new UserException("Enter valid email id");
			Seller sellerLogin = sellerRepo.findByEmail(email);
			if(sellerLogin == null)
			throw new UserException("Wrong seller credentials");
			if(!sellerLogin.getPassword().equals(password))
	        throw new UserException("Wrong seller credentials");
			sellerLogin.setIsActive("Yes");
			sellerRepo.save(sellerLogin);
			return "Seller successfully login";
		}
		
//deleting seller account
		@Override
		public String DeleteSeller(String sellerId) {
			Seller seller1 = sellerRepo.findBySellerId(sellerId);
			sellerRepo.delete(seller1);
			return "Seller deleted successfully";
		}
		
}
