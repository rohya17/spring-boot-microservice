package com.eshop.enduser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.enduser.models.CartItems;
import com.eshop.enduser.service.CartService;


@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired private CartService cartService;
	
	@PutMapping("/updateCart")
	public ResponseEntity<Object> updateCart(
			@RequestParam(value="endUserId",required = true) Integer endUserId,
			@RequestBody List<CartItems> cartItems){
		return cartService.updateCart( endUserId, cartItems );
	}
	
}
