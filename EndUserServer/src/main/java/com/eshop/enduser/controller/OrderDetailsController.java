package com.eshop.enduser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.enduser.service.OrderDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@CrossOrigin("*")
@RestController("OrderDetailsController")
@RequestMapping("/orders")
public class OrderDetailsController {

	@Autowired OrderDetailsService orderDetailsService;
	
	@Operation(summary = "Gets cart total for end user", description = "gets cart total for given end user id")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Cart is empty."),
	        @ApiResponse(responseCode = "200", description = "response with all order details for given end user id"),
	        @ApiResponse(responseCode = "500", description = "Response body is null")
	    })
	@GetMapping("/getTotal")
	public ResponseEntity<Object> getCartTotal( @Valid @Min(value = 1, message="end user id can not be zero or negative") @RequestParam Integer endUserId ){
		return orderDetailsService.getCartTotal(endUserId);
	}
	
	@Operation(summary = "Gets all orders from order table", description = "gets all user's order from databse table")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "No orders pending."),
	        @ApiResponse(responseCode = "200", description = "response with all order details"),
	        @ApiResponse(responseCode = "500", description = "Response body is null")
	    })
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllOrders( ){
		return orderDetailsService.getAllOrders();
	}
	
}
