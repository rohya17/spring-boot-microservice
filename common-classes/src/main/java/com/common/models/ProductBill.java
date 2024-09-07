package com.common.models;

public class ProductBill {

	private String eshopName;
	private String productName;
	private String pricePerItem;
	private String discount;
	private String quantity;
	private double totalWithDiscount;
	private double totalWoDiscount;

	public ProductBill() {
		super();
	}

	public ProductBill(Product product, int quantity) {
		this.eshopName = product.getEshopName();
		this.pricePerItem = String.valueOf(product.getPrice());
		this.productName = product.getProductName();
		this.discount = String.valueOf(product.getPercentDiscount());
		this.quantity = String.valueOf(quantity);
		this.totalWoDiscount = product.getPrice() * quantity;
		this.totalWithDiscount = (product.getPercentDiscount() * this.totalWoDiscount) / 100;;
	}

	public String getEshopName() {
		return eshopName;
	}

	public void setEshopName(String eshopName) {
		this.eshopName = eshopName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPricePerItem() {
		return pricePerItem;
	}

	public void setPricePerItem(String pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public double getTotalWithDiscount() {
		return totalWithDiscount;
	}

	public void setTotalWithDiscount(double totalWithDiscount) {
		this.totalWithDiscount = totalWithDiscount;
	}

	public double getTotalWoDiscount() {
		return totalWoDiscount;
	}

	public void setTotalWoDiscount(double totalWoDiscount) {
		this.totalWoDiscount = totalWoDiscount;
	}
	
}
