package com.common.models;

import java.util.List;

public class OrderBill {

	private String grandTotalWithDiscount;
	private String grandTotalWoDiscount;
	private List<ProductBill> productsBill;
	
	public String getGrandTotalWithDiscount() {
		return grandTotalWithDiscount;
	}
	public void setGrandTotalWithDiscount(String grandTotalWithDiscount) {
		this.grandTotalWithDiscount = grandTotalWithDiscount;
	}
	public String getGrandTotalWoDiscount() {
		return grandTotalWoDiscount;
	}
	public void setGrandTotalWoDiscount(String grandTotalWoDiscount) {
		this.grandTotalWoDiscount = grandTotalWoDiscount;
	}
	public List<ProductBill> getProductsBill() {
		return productsBill;
	}
	public void setProductsBill(List<ProductBill> productsBill) {
		this.productsBill = productsBill;
	}
	public void addToGrandTotalWithDiscount(double totalWithDiscount) {
		this.grandTotalWithDiscount += totalWithDiscount;
	}
	public void addToGrandTotalWoDiscount(double totalWoDiscount) {
		this.grandTotalWoDiscount += totalWoDiscount;
	}
	
}
