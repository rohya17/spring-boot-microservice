package com.eshop.enduser.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.common.models.OrderBill;
import com.common.models.Product;
import com.common.models.ProductBill;
import com.eshop.enduser.clients.EshopOwnerUserClient;
import com.eshop.enduser.models.CartItems;
import com.eshop.enduser.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderDetailsService {

	@Autowired private CartRepository cartRepository;
	@Autowired private EshopOwnerUserClient eshopOwnerUserClient;
	
	public ResponseEntity<Object> getCartTotal( Integer endUserId ) {

		List<CartItems> cartItems = cartRepository.findByEndUserId( endUserId );
		if(cartItems.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is empty.");
		}
		return getBillForOrder( cartItems );
	}

	public ResponseEntity<Object> getAllOrders() {
		List<CartItems> allOrders = cartRepository.findAll();
		return getBillForOrder( allOrders );
	}
	
	private ResponseEntity<Object> getBillForOrder(List<CartItems> cartItems) {
		
		List<Integer> productIds = cartItems.stream().map(cartItem -> cartItem.getProductId()).toList();
		if(productIds.isEmpty()) {
			return ResponseEntity.ok("Cart is empty !!!");
		}
		ResponseEntity<Object> response = eshopOwnerUserClient.getProducts( productIds );
		if(response.getStatusCode() != HttpStatus.OK) {
			return response;
		}
		if (!(response.getBody() instanceof List)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Response not valid from client.");
		}
		Map<Integer,Integer> productQuantity = new HashMap<>();
		for (CartItems item : cartItems) {
			productQuantity.put(item.getProductId(), item.getQuantity());
		}
		
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) response.getBody();
		List<ProductBill> productBills = new ArrayList<>();
		for (Product product : products) {
			int quantity = productQuantity.get(product.getId());
			ProductBill productBill = new ProductBill( product, quantity );
			productBills.add(productBill);
		}
		OrderBill orderBill = new OrderBill();
		orderBill.setProductsBill(productBills);
		productBills.forEach((pb) -> {
			orderBill.addToGrandTotalWithDiscount(pb.getTotalWithDiscount());
			orderBill.addToGrandTotalWoDiscount(pb.getTotalWoDiscount());
		});
		return ResponseEntity.ok(orderBill);
	}
	
}
