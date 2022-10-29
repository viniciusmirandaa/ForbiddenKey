package com.devsuperior.dscatlog.tests;

import java.time.Instant;

import com.forbiddenkey.dto.ProductDTO;
import com.forbiddenkey.entities.Category;
import com.forbiddenkey.entities.Product;

public class Factory {

	public static Product createProduct() {
		Product product = new Product();
		product.getCategories().add(new Category(2L, "Electronics"));
		return product;
	}

	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		return new ProductDTO(product, product.getCategories());
	}
}
