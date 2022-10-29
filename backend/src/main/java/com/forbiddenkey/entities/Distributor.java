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

	public Distributor(Long id, String name, List<Product> distributorProducts, Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.name = name;
		this.distributorProducts = distributorProducts;
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