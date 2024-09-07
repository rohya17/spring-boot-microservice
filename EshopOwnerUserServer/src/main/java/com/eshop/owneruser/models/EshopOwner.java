package com.eshop.owneruser.models;

import com.common.models.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "eshopowner")
public class EshopOwner extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "eshopId", referencedColumnName = "id")
	private Eshop eshop;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Eshop getEshop() {
		return eshop;
	}

	public void setEshop(Eshop eshop) {
		this.eshop = eshop;
	}
	
}
