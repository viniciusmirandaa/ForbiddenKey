package com.forbiddenkey.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_distributor")
public class Distributor extends DomainEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	@OneToMany(mappedBy = "distributor")
	private List<Product> distributorProducts = new ArrayList<>();

	public Distributor() {

	}

	public Distributor(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getDistributorProducts() {
		return distributorProducts;
	}

	public void setDistributorProducts(List<Product> distributorProducts) {
		this.distributorProducts = distributorProducts;
	}
}