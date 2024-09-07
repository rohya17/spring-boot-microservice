package com.eshop.owneruser.models;

import java.util.List;

import com.common.models.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "eshop")
public class Eshop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "E-shop name is mandatory")
	@Size(min = 5, max = 20, message = "E-shop name should be min 5 max 20 character long")
	private String eshopName;
	@NotBlank(message = "E-shop search tags are mandatory")
	@Size(min = 10, max = 500, message = "E-shop search tags should be min 10 max 500 character long")
	private String eshopSearchTags;
	@NotBlank(message = "E-shop description is mandatory")
	@Size(min = 10, max = 500, message = "E-shop description should be min 10 max 500 character long")
	private String eshopDescription;
	private String eshopCoverImage;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "eshopId")
	private List<Product> eshopProducts;
	
	@OneToOne(mappedBy = "eshop")
    private EshopOwner owner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEshopName() {
		return eshopName;
	}

	public void setEshopName(String eshopName) {
		this.eshopName = eshopName;
	}

	public String getEshopSearchTags() {
		return eshopSearchTags;
	}

	public void setEshopSearchTags(String eshopSearchTags) {
		this.eshopSearchTags = eshopSearchTags;
	}

	public String getEshopDescription() {
		return eshopDescription;
	}

	public void setEshopDescription(String eshopDescription) {
		this.eshopDescription = eshopDescription;
	}

	public String getEshopCoverImage() {
		return eshopCoverImage;
	}

	public void setEshopCoverImage(String eshopCoverImage) {
		this.eshopCoverImage = eshopCoverImage;
	}

	public List<Product> getEshopProducts() {
		return eshopProducts;
	}

	public void setEshopProducts(List<Product> eshopProducts) {
		this.eshopProducts = eshopProducts;
	}

//	public EshopOwner getOwner() {
//		return owner;
//	}
//
//	public void setOwner(EshopOwner owner) {
//		this.owner = owner;
//	}
	
}
