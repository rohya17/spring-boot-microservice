package com.eshop.enduser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.enduser.service.OrderDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderDetailsController {

	@Autowired OrderDetailsService orderDetailsService;
	
	@GetMapping("/getTotal")
	public ResponseEntity<Object> getCartTotal( @RequestParam("endUserId")Integer endUserId ){
		return orderDetailsService.getCartTotal(endUserId);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllOrders( ){
		return orderDetailsService.getAllOrders();
	}
	
}
