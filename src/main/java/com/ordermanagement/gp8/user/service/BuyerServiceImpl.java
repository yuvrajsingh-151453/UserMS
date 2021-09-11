package com.ordermanagement.gp8.user.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ordermanagement.gp8.user.dto.BuyerDTO;
import com.ordermanagement.gp8.user.dto.CartDTO;
import com.ordermanagement.gp8.user.entity.Buyer;
import com.ordermanagement.gp8.user.entity.Cart;
import com.ordermanagement.gp8.user.entity.Wishlist;
import com.ordermanagement.gp8.user.exception.UserException;
import com.ordermanagement.gp8.user.repository.BuyerRepository;
import com.ordermanagement.gp8.user.repository.CartRepository;
import com.ordermanagement.gp8.user.repository.WishlistRepository;
import com.ordermanagement.gp8.user.utility.Userpk;
import com.ordermanagement.gp8.user.validator.UserValidator;

@Service(value = "buyerService")
@Transactional
public class BuyerServiceImpl implements BuyerService {
	
@Autowired
 BuyerRepository buyerRepo;
@Autowired
	 WishlistRepository wishlistRepo;
@Autowired
	 CartRepository cartRepo;
	
	//buyer registration
	@Override
	public String BuyerRegister(BuyerDTO buyerDTO) throws UserException {
		Buyer buyer = buyerRepo.findByPhoneNumber(buyerDTO.getPhoneNumber());
		if(buyer != null)
		throw new UserException("Buyer already exist");
		buyer = new Buyer();
		buyer.setBuyerId(buyerDTO.getBuyerId());
		buyer.setName(buyerDTO.getName());
		buyer.setEmail(buyerDTO.getEmail());
		buyer.setPhoneNumber(buyerDTO.getPhoneNumber());
		buyer.setPassword(buyerDTO.getPassword());
		buyer.setIsActive("Yes");
		buyer.setIsPrivileged("No");
		buyer.setRewardPoints("0");
		buyerRepo.save(buyer);
		return "Buyer successfully registered with buyer Id : "+buyer.getBuyerId();
	}
	
	
//get all buyers
	@Override
	public List<BuyerDTO> getAllBuyers() throws UserException {
		
		List<Buyer> list = buyerRepo.findAll();
		if(list.isEmpty())
		throw new UserException("Buyerlist is empty");
		List<BuyerDTO> list1 = new ArrayList<>();
		list.forEach(list2 -> {
			BuyerDTO buy = new BuyerDTO();
			buy.setBuyerId(list2.getBuyerId());
			buy.setName(list2.getName());
			buy.setEmail(list2.getEmail());
			buy.setPhoneNumber(list2.getPhoneNumber());
			buy.setPassword(list2.getPassword());
			buy.setIsActive(list2.getIsActive());
			buy.setIsPrivileged(list2.getIsPrivileged());
			buy.setRewardPoints(list2.getRewardPoints());
			list1.add(buy);
		}
		);
		return list1;
	}

//get buyer by Id
		@Override
		public BuyerDTO getBuyerBybuyerId(String buyerId) throws UserException {
			Buyer buy1 = buyerRepo.findByBuyerId(buyerId);
			if(buy1 == null)
			throw new UserException("Buyer not available");
			BuyerDTO buyerDTO = new BuyerDTO();
			buyerDTO.setBuyerId(buy1.getBuyerId());
			buyerDTO.setName(buy1.getName());
			buyerDTO.setEmail(buy1.getEmail());
			buyerDTO.setPhoneNumber(buy1.getPhoneNumber());
			buyerDTO.setPassword(buy1.getPassword());
			buyerDTO.setIsActive(buy1.getIsActive());
			buyerDTO.setIsPrivileged(buy1.getIsPrivileged());
			buyerDTO.setRewardPoints(buy1.getRewardPoints());
			return buyerDTO;
		}
	
//login as buyer
	@Override
	public String BuyerLogin(String email, String password) throws UserException {
	     if(!UserValidator.validateEmail(email))
	    throw new UserException("Enter the valid email ");
	    Buyer buylogin = buyerRepo.findByEmail(email);
	    if(buylogin == null)
		throw new UserException("Wrong Login credentials");
		if(!buylogin.getPassword().equals(password))throw new UserException("Wrong Login credentials");
		buylogin.setIsActive("yes");
		buyerRepo.save(buylogin);
		return "Buyer successfully Login";
	}

	
//delete buyer account
	@Override
	public String DeleteBuyer(String buyerId){
		Buyer buyer1 = buyerRepo.findByBuyerId(buyerId);
		buyerRepo.delete(buyer1);
		return "Buyer deleted successfully";
	}

	// adding to cart
	@Override
	public String AddtoCart(String prodId, String buyerId, Integer quantity) {
		Userpk user = new Userpk(prodId,buyerId);
	    Cart cart = new Cart();
		cart.setUserpk(user);
		cart.setQuantity(quantity);
		cartRepo.save(cart);
		return "Product added to Cart";
	}
	
//getting cart item
	@Override
	public List<CartDTO> getCartProducts(String buyerId) throws UserException {
		List<Cart> list = cartRepo.findByUserpkBuyerId(buyerId);
		if(list.isEmpty())
		throw new UserException("Cart is empty");
		List<CartDTO> list1 = new ArrayList<>();
		for(Cart cart : list)
		{
		 CartDTO cartDTO = new CartDTO();
		 cartDTO.setBuyerId(cart.getUserpk().getBuyerId());
		 cartDTO.setProdId(cart.getUserpk().getProdId());
		 cartDTO.setQuantity(cart.getQuantity());
		 list1.add(cartDTO);
		}
		return list1;
	}
	
//removing product from cart
	@Override
	public String RemoveFromCart(String buyerId, String prodId) throws UserException {
		Cart cart1 = cartRepo.findByUserpkBuyerIdAndUserpkProdId(buyerId, prodId);
		if(cart1==null)
		throw new UserException("No such products In Cart");
		cartRepo.deleteByUserpkBuyerIdAndUserpkProdId(buyerId, prodId);
		return "Successfully removed the product";
	}
	
	//adding product to wishlist
	@Override
	public String AddtoWishlist(String prodId, String buyerId) {
		Userpk user1 = new Userpk(prodId,buyerId);
	    Wishlist wishlist = new Wishlist();
		wishlist.setUserId(user1);
		wishlistRepo.save(wishlist);
		return "Product added to wishlist";
	}
	//remove from wishlist
	@Override
	public String RemoveFromWishlist(String buyerId, String prodId) throws UserException {
	Wishlist wlist = wishlistRepo.findByUserIdBuyerIdAndUserIdProdId(buyerId, prodId);
		if(wlist==null)
		throw new UserException("No such products In wishlist");
		wishlistRepo.deleteByUserIdBuyerIdAndUserIdProdId(buyerId, prodId);
		return "Successfully Removed the product";
	}
	

	//updation of reward points 
	@Override
	public String UpdateRewPoints(String buyerId, Integer rewardPoints) throws UserException {
	    Buyer buy = buyerRepo.findByBuyerId(buyerId);
		if(buy==null)
		throw new UserException("Buyer not found");
		buy.setRewardPoints(rewardPoints.toString());
		buyerRepo.save(buy);
		return "Updated the RewPoints for buyer Id : "+ buy.getBuyerId();
	}
	
}

