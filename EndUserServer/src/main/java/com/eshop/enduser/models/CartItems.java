package com.eshop.enduser.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "cart")
public class CartItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Min(value=1 , message = "End user id can not be zero")
	private Integer endUserId;
	@Min(value=1 , message = "Product id can not be zero")
	private Integer productId;
	@Max(value=10, message="Maximum 10 quantities are allowed")
	private Integer quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getEndUserId() {
		return endUserId;
	}

	public void setEndUserId(Integer endUserId) {
		this.endUserId = endUserId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
