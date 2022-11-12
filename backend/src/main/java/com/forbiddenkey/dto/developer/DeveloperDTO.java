package com.forbiddenkey.dto.developer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.forbiddenkey.dto.product.ProductDTO;
import com.forbiddenkey.entities.Developer;
import com.forbiddenkey.entities.Product;

public class DeveloperDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private List<ProductDTO> developerProduct = new ArrayList<>();

	public DeveloperDTO() {

	}

	public DeveloperDTO(Long id, String name, List<ProductDTO> developerProduct) {
		this.id = id;
		this.name = name;
		this.developerProduct = developerProduct;
	}

	public DeveloperDTO(Developer entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public DeveloperDTO(Developer entity, List<Product> developerProduct) {
		this.id = entity.getId();
		this.name = entity.getName();
		developerProduct
				.forEach(product -> this.developerProduct.add(new ProductDTO(product, product.getCategories())));
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

	public List<ProductDTO> getDeveloperProduct() {
		return developerProduct;
	}
}