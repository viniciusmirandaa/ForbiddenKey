package com.forbiddenkey.dto.category;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.forbiddenkey.dto.product.ProductDTO;
import com.forbiddenkey.entities.Category;
import com.forbiddenkey.entities.Product;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Set<ProductDTO> products = new HashSet<>();

	public CategoryDTO() {

	}

	public CategoryDTO(Long id, String name, Set<ProductDTO> products) {
		this.id = id;
		this.name = name;
		this.products = products;
	}

	public CategoryDTO(Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public CategoryDTO(Category entity, Set<Product> products) {
		this.id = entity.getId();
		this.name = entity.getName();
		products.forEach(product -> this.getProducts().add(new ProductDTO(product)));
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

	public Set<ProductDTO> getProducts() {
		return products;
	}

}