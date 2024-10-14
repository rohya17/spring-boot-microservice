package com.eshop.enduser.clients;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EshopOwnerUserClientFallback implements EshopOwnerUserClient {

	@Override
	public ResponseEntity<Object> checkIfProductsAreValid(List<Integer> productIds) {
		return ResponseEntity
				.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body("Service unavailable at the moment.");
	}

	@Override
	public ResponseEntity<Object> getProducts(List<Integer> productIds) {
		return ResponseEntity
				.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body("Service unavailable at the moment.");
	}

}
