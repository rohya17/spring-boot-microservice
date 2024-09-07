package com.eshop.enduser.models;

import java.util.List;

import com.common.models.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "enduser")
public class EndUser extends User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "endUserId")
	private List<CartItems> cart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CartItems> getCart() {
		return cart;
	}

	public void setCart(List<CartItems> cart) {
		this.cart = cart;
	}
	
}
