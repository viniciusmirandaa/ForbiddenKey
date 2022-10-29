
package com.devsuperior.dscatlog.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.forbiddenkey.dto.ProductDTO;
import com.forbiddenkey.repositories.ProductRepository;
import com.forbiddenkey.services.ProductService;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class ProductServiceIT {

	@Autowired
	private ProductService service;

	@Autowired
	private ProductRepository repository;

	private long existingId;
	private long nonExistingId;
	private long countTotalProducts;

	@BeforeEach
	void setup() throws Exception {
		
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	}

	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {
		
		service.delete(existingId);

		Assertions.assertEquals(countTotalProducts - 1, repository.count());
	}

	@Test
	public void deleteShoudThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}

	@Test
	public void findAllShouldReturnPageWhenPage0Size10() {
		
		PageRequest pageRequest = PageRequest.of(0, 10);

		Page<ProductDTO> page = service.findAllPaged(pageRequest);

		Assertions.assertFalse(page.isEmpty());
		Assertions.assertEquals(countTotalProducts, page.getTotalElements());
		Assertions.assertEquals(0, page.getNumber());
	}

	@Test
	public void findAllShouldNotReturnPageWhenPageDoesNotExist() {
		
		PageRequest pageRequest = PageRequest.of(5, 10);

		Page<ProductDTO> page = service.findAllPaged(pageRequest);

		Assertions.assertTrue(page.isEmpty());
	}
	
	@Test
	public void findAllShouldReturnSortedPageWhenSortByName() {
		
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));

		Page<ProductDTO> page = service.findAllPaged(pageRequest);

		Assertions.assertEquals("Macbook Pro", page.getContent().get(0).getName());
	}
	

	@Test
	public void findAllShouldReturnSortedPageWhenSortByPrice() {
		
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("price").descending());

		Page<ProductDTO> page = service.findAllPaged(pageRequest);

		Assertions.assertEquals(4170.0, page.getContent().get(0).getPrice());
	}
	
}
