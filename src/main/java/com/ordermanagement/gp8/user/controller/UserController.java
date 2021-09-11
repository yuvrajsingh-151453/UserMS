package com.ordermanagement.gp8.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.ordermanagement.gp8.user.dto.BuyerDTO;
import com.ordermanagement.gp8.user.dto.CartDTO;
import com.ordermanagement.gp8.user.dto.ProductDTO;
import com.ordermanagement.gp8.user.dto.SellerDTO;
import com.ordermanagement.gp8.user.exception.UserException;
import com.ordermanagement.gp8.user.service.BuyerService;
import com.ordermanagement.gp8.user.service.SellerService;

@RestController
//@CrossOrigin
@RequestMapping(value = "api")
public class UserController {

	@Autowired
    BuyerService buyerservice;
	@Autowired
   SellerService sellerservice;

	

	@Autowired
	Environment environment;

	@Value("${product.uri}")
	String prodUri;

	// registration of buyer
	@PostMapping(value = "/buyer/register")
	public ResponseEntity<String> BuyerRegistration(@Valid @RequestBody BuyerDTO buyerDTO) {
		try {
			String successmessage = buyerservice.BuyerRegister(buyerDTO);
			return new ResponseEntity<>(successmessage, HttpStatus.OK);
		} catch (UserException exp) {
			String error = environment.getProperty(exp.getMessage());
			return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
//get all buyers
		@GetMapping(value = "/buyer/getAll")
		public ResponseEntity<List<BuyerDTO>> viewAllBuyers() {
			try {
				List<BuyerDTO> list = buyerservice.getAllBuyers();
				return new ResponseEntity<>(list, HttpStatus.OK);
			} catch (Exception exception) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
			}
		}
		
		
//get buyer by id
@GetMapping(value = "/buyer/{buyerId}")
	public ResponseEntity<BuyerDTO> viewBuyerbyId(@PathVariable String buyerId) {
			try {
				BuyerDTO buyerDTO = buyerservice.getBuyerBybuyerId(buyerId);
				return new ResponseEntity<>(buyerDTO, HttpStatus.OK);
			} catch (Exception exception) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
						exception);
			}
		}
		
	
// login as buyer
	@PostMapping(value = "/buyer/login/{email}/{password}")
	public ResponseEntity<String> loginasBuyer(@PathVariable String email, @PathVariable String password) {
		try {
			String successmessage = buyerservice.BuyerLogin(email, password);
			return new ResponseEntity<>(successmessage, HttpStatus.OK);
		} catch (UserException exp) {
			String error = environment.getProperty(exp.getMessage());
			return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
		}
	}

// deactivate buyer
	@DeleteMapping(value = "/buyer/deactivate/{buyerId}")
	public ResponseEntity<String> deactivateBuyer(@PathVariable String buyerId) {

		String successmessage = buyerservice.DeleteBuyer(buyerId);

		return new ResponseEntity<>(successmessage, HttpStatus.OK);

	}

// registration of seller
	@PostMapping(value = "/seller/register")
	public ResponseEntity<String> SellerRegistration(@Valid @RequestBody SellerDTO sellerDTO) {
        try {
			String successmessage = sellerservice.SellerRegister(sellerDTO);
			return new ResponseEntity<>(successmessage, HttpStatus.OK);
		} catch (UserException exp) {
			String error = environment.getProperty(exp.getMessage());
			return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
//get seller by id
		@GetMapping(value = "/seller/{sellerId}")
		public ResponseEntity<SellerDTO> viewSellerbyId(@PathVariable String sellerId) {
			try {
				SellerDTO sellerDTO = sellerservice.getSellerBysellerId(sellerId);
				return new ResponseEntity<>(sellerDTO, HttpStatus.OK);
			} catch (Exception exception) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
						exception);
			}
		}
		
		
//get all sellers
		@GetMapping(value = "/seller/getAll")
		public ResponseEntity<List<SellerDTO>> viewAllSellers() {
			try {
				List<SellerDTO> li = sellerservice.getAllSellers();
				return new ResponseEntity<>(li, HttpStatus.OK);
			} catch (Exception exception) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
			}
		}
		
// login as seller
	@PostMapping(value = "/seller/login/{email}/{password}")
	public ResponseEntity<String> loginasSeller(@PathVariable String email, @PathVariable String password) {
		try {
			String successmessage = sellerservice.SellerLogin(email, password);
			return new ResponseEntity<String>(successmessage, HttpStatus.OK);
		} catch (UserException exp) {
			String error = environment.getProperty(exp.getMessage());
			return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
		}
	}

// deactivate seller
	@DeleteMapping(value = "/seller/deactivate/{sellerId}")
	public ResponseEntity<String> deactivateSeller(@PathVariable String sellerId) {

		String successmessage = sellerservice.DeleteSeller(sellerId);

		return new ResponseEntity<>(successmessage, HttpStatus.OK);
	}

	
// adding to cart
//	@PostMapping(value = "/buyer/addtocart/{buyerId}/{prodId}/{quantity}")
//	public ResponseEntity<String> addToCart(@PathVariable String buyerId, @PathVariable String prodId,
//			@PathVariable Integer quantity) throws UserException {
//		try {
//
//			ProductDTO product = new RestTemplate().getForObject(prodUri + "api/product/ById/" + prodId, ProductDTO.class);
//			System.out.println(product);
//			System.out.println(product instanceof ProductDTO);
//			String successmessage = buyerservice.AddtoCart(product.getProdId(), buyerId, quantity);
//
//			return new ResponseEntity<>(successmessage, HttpStatus.ACCEPTED);
//		} catch (Exception exp) {
//			String error = "error";
//			if (exp.getMessage().equals("404 null")) {
//				error = "No such product to add ";
//			}
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, error, exp);
//		}
//	}

// getting cart
	@GetMapping(value = "/buyer/getcart/{buyerId}")
	public ResponseEntity<List<CartDTO>> getFromCart(@PathVariable String buyerId) throws UserException {

		try {
			List<CartDTO> list = buyerservice.getCartProducts(buyerId);

			return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
		} catch (UserException exp) {
			System.out.println(exp.getMessage());
			String error = exp.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, error, exp);

		}
	}
	

//remove from cart
	@PostMapping(value = "/buyer/removecart/{buyerId}/{prodId}")
	public ResponseEntity<String> removeCart(@PathVariable String buyerId, @PathVariable String prodId)
			throws UserException {

		try {
			String successmessage = buyerservice.RemoveFromCart(buyerId, prodId);

			return new ResponseEntity<>(successmessage, HttpStatus.OK);
		} catch (UserException exp) {
			String error = exp.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, error, exp);

		}
	}
	
// adding to wishlist
//	@PostMapping(value = "buyer/addtowishlist/{buyerId}/{prodId}")
//	public ResponseEntity<String> addToWishlist(@PathVariable String buyerId, @PathVariable String prodId)
//			throws UserException {
//		try {
//
//			ProductDTO product = new RestTemplate().getForObject(prodUri + "api/product/ById/" + prodId, ProductDTO.class);
//
//			String successmessage = buyerservice.AddtoWishlist(product.getProdId(), buyerId);
//
//			return new ResponseEntity<>(successmessage, HttpStatus.ACCEPTED);
//		} catch (Exception exp) {
//			System.out.println(exp);
//			String error = "error";
//			if (exp.getMessage().equals("404 null")) {
//				error = "No such products to add";
//			}
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, error, exp);
//		}
//	}
	
	
//remove from wishlist
	@PostMapping(value = "/buyer/removewishlist/{buyerId}/{prodId}")
	public ResponseEntity<String> removeWishlist(@PathVariable String buyerId, @PathVariable String prodId)
			throws UserException {

		try {
			String successmessage = buyerservice.RemoveFromWishlist(buyerId, prodId);

			return new ResponseEntity<>(successmessage, HttpStatus.OK);
		} catch (UserException exp) {
			String error = exp.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, error, exp);

		}
	}
	

//updation of Rewardpoints
	@GetMapping(value = "/updateRewardPoints/{buyerId}/{rewPoints}")
	public ResponseEntity<String> updatationofRewardPoints(@PathVariable String buyerId,
			@PathVariable Integer rewPoints) {
		try {
			String successmessage = buyerservice.UpdateRewPoints(buyerId, rewPoints);
			return new ResponseEntity<>(successmessage, HttpStatus.OK);
		} catch (UserException exp) {
			String error = exp.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, error, exp);

		}

}
	
	
}