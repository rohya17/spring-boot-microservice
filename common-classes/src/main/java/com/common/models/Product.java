package com.common.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="product")
public abstract class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Product name is mandatory")
	@Size(min=5,max =200)
	private String productName;
	@NotBlank(message = "Product description is mandatory")
	@Size(min=10,max =500)
	private String productDescription;
	@NotBlank(message = "Search tags are mandatory")
	@Size(min=10,max =500)
	private String searchTags;
	private String productImage;
	@NotNull(message = "Shop id is mandatory")
	@Min(1)
	private Integer eshopId;
	@NotNull(message = "Product price is mandatory")
	@Min(0)
	private Double price;
	private boolean outOfStock;
	private boolean deleted;
	private Integer percentDiscount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getSearchTags() {
		return searchTags;
	}
	public void setSearchTags(String searchTags) {
		this.searchTags = searchTags;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public Integer getEshopId() {
		return eshopId;
	}
	public void setEshopId(Integer eshopId) {
		this.eshopId = eshopId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public boolean isOutOfStock() {
		return outOfStock;
	}
	public void setOutOfStock(boolean outOfStock) {
		this.outOfStock = outOfStock;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Integer getPercentDiscount() {
		return percentDiscount;
	}
	public void setPercentDiscount(Integer percentDiscount) {
		this.percentDiscount = percentDiscount;
	}
	
	@Transient
	private String eshopName;

	public String getEshopName() {
		return eshopName;
	}
	public void setEshopName(String eshopName) {
		this.eshopName = eshopName;
	}
	
}
