package com.forbiddenkey.dto.distributor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.forbiddenkey.dto.product.ProductDTO;
import com.forbiddenkey.entities.Distributor;
import com.forbiddenkey.entities.Product;

public class DistributorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private List<ProductDTO> distributorProducts = new ArrayList<>();

	public DistributorDTO() {

	}

	public DistributorDTO(Long id, String name, List<ProductDTO> distributorProducts) {
		this.id = id;
		this.name = name;
		this.distributorProducts = distributorProducts;
	}

	public DistributorDTO(Distributor entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public DistributorDTO(Distributor entity, List<Product> distributorProducts) {
		this.id = entity.getId();
		this.name = entity.getName();
		entity.getDistributorProducts().forEach(
				product -> this.getDistributorProducts().add(new ProductDTO(product, product.getCategories())));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductDTO> getDistributorProducts() {
		return distributorProducts;
	}
}
