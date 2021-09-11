package com.ordermanagement.gp8.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SellerDTO {
	
	String sellerId;
	@NotNull(message = "Please provide customer name")
//	@Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Name should contain only alphabets and space")
	 String name;
	@Email(message = "Please provide valid email address")
    @NotNull(message = "Please provide email address")
	 String email;
	@NotNull(message = "Please provide phone number")
	@Size(min=10,max=10)
	@Pattern(regexp="(^[7-9][0-9]{9})", message = "Phone number should be 10 digits only")
	 String phoneNumber;
	@NotNull(message = "Please provide password")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#!@$%^&*]).{7,20}$", 
			message = "It should be 7 to 20 characters in length (both inclusive). It should contain at least one uppercase, at least one lowercase, at least one digit. "
					+ "It should also contain a special character amongst -! @, #, $, %, ^, &, *")
	 String password;
	 String isActive;
	
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	

}
