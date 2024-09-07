package com.eshop.enduser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eshop.enduser.clients.EshopOwnerUserClient;
import com.eshop.enduser.models.CartItems;
import com.eshop.enduser.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartService {

	@Autowired private CartRepository cartRepository;
	@Autowired private EshopOwnerUserClient eshopOwnerUserClient;

	public ResponseEntity<Object> updateCart(Integer endUserId, List<CartItems> cartItems) {

		if(!cartItems.isEmpty()) {
			List<Integer> productIds = cartItems.stream().map(CartItems::getProductId).toList();
			ResponseEntity<Object> response = eshopOwnerUserClient.checkIfProductsAreValid( productIds );
			if(response.getStatusCode() != HttpStatus.OK) {
				return response;
			}
			cartRepository.saveAll(cartItems);
		}else {
			cartRepository.deleteAllByEndUserId(endUserId);
		}
		
		return ResponseEntity.ok("Cart items saved successfully.");
	}
	
}
