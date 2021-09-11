package com.ordermanagement.gp8.user.validator;

import com.ordermanagement.gp8.user.dto.BuyerDTO;
import com.ordermanagement.gp8.user.dto.SellerDTO;
import com.ordermanagement.gp8.user.exception.UserException;

public class UserValidator {
	
	public static void validateBuyer(BuyerDTO buyer) throws UserException {
		
		
		
		if(!validateEmail(buyer.getEmail()))
			throw new UserException("Validator.ENTER_VALID_EMAIL");
			
	}
	
	public static void validateSeller(SellerDTO buyer) throws UserException {
		
		
		if(!validateEmail(buyer.getEmail()))
			throw new UserException("Validator.ENTER_VALID_EMAIL");
		
	}
	
	public static boolean validateEmail(String email)
	{
		String regex = "[A-za-z]+@[A-za-z]+[\\.]com";
		
		if(email.matches(regex))
			return true;
		
		return false;
	}
	
	
	
}