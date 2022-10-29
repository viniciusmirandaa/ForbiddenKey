package com.devsuperior.dscatlog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatlog.tests.Factory;
import com.forbiddenkey.entities.Product;
import com.forbiddenkey.repositories.ProductRepository;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;

	private long existingId;
	private long nonExistingId;
	private long countTotalProducts;

	@BeforeEach
	void setup() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25;
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {

		repository.deleteById(existingId);

		Optional<Product> result = repository.findById(existingId);

		Assertions.assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldNotDeleteObjectWhenIdNotExist() {

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});

	}

	@Test
	public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {

		Product product = Factory.createProduct();
		product.setId(null);

		product = repository.save(product);

		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalProducts + 1, product.getId());
	}

	@Test
	public void findByIdShouldReturnEmptyObjectWhenIdDoesNotExists() {
		Optional<Product> optional = repository.findById(nonExistingId);

		Assertions.assertTrue(optional.isEmpty());
	}

	@Test
	public void findByIdShouldReturnObjectWhenIdExists() {
		Optional<Product> optional = repository.findById(existingId);

		Assertions.assertTrue(optional.isPresent());
	}
}
