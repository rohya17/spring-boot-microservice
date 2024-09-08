package com.eshop.enduser.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull(message = "User id can not be null")
	@Min(1)
	private Integer endUserId;
	@NotNull(message = "User id can not be null")
	@Min( value = 1, message = "Product id can not be less that 1")
	private Integer productId;
	@Min( value = 0, message ="Billing amount can not be null")
	private Double billingAmount;
	@Min(value = 1, message = "Minimum 1 unit shold be selected")
	@Max(value = 10, message = "Maximum 10 units are allowed")
	private Integer quantity;
	@Min(value = 0, message = "Minimum 0 rating should be provided")
	@Max(value = 5, message = "Maximum 5 ratings are allowed")
	private Integer userRatings;
	private String reviewComment;
	private String reviewImage;
	private boolean cancelled;
	private boolean returned;
	private boolean dispatched;
	private boolean delivered;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Double getBillingAmount() {
		return billingAmount;
	}
	public void setBillingAmount(Double billingAmount) {
		this.billingAmount = billingAmount;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getUserRatings() {
		return userRatings;
	}
	public void setUserRatings(Integer userRatings) {
		this.userRatings = userRatings;
	}
	public String getReviewComment() {
		return reviewComment;
	}
	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}
	public String getReviewImage() {
		return reviewImage;
	}
	public void setReviewImage(String reviewImage) {
		this.reviewImage = reviewImage;
	}
	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	public boolean isReturned() {
		return returned;
	}
	public void setReturned(boolean returned) {
		this.returned = returned;
	}
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
	public boolean isDispatched() {
		return dispatched;
	}
	public void setDispatched(boolean dispatched) {
		this.dispatched = dispatched;
	}
	public boolean isDelivered() {
		return delivered;
	}
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
	
}
