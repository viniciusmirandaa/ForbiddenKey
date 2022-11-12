package com.devsuperior.dscatlog.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.dscatlog.tests.Factory;
import com.forbiddenkey.dto.product.ProductDTO;
import com.forbiddenkey.entities.Product;
import com.forbiddenkey.repositories.CategoryRepository;
import com.forbiddenkey.repositories.ProductRepository;
import com.forbiddenkey.services.ProductService;
import com.forbiddenkey.services.exceptions.DatabaseException;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
public class ProductServicesTests {

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	@Mock
	private CategoryRepository categoryRepository;

	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private Product product;
	private PageImpl<Product> page;

	@BeforeEach
	void setup() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 4L;
		product = Factory.createProduct();
		page = new PageImpl<>(List.of(product));

		when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

		when(repository.save(ArgumentMatchers.any())).thenReturn(product);

		when(repository.getOne(existingId)).thenReturn(product);
		when(repository.getOne(nonExistingId)).thenThrow(ResourceNotFoundException.class);

		when(repository.findById(existingId)).thenReturn(Optional.of(product));
		when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

		doNothing().when(repository).deleteById(existingId);
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
	}

	@Test
	public void updateShouldReturnProductDTOWhenIdExist() {
		ProductDTO entity = Factory.createProductDTO();
		ProductDTO dto = service.update(existingId, entity);

		Assertions.assertNotNull(dto);
	}

	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		ProductDTO entity = Factory.createProductDTO();

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			ProductDTO dto = service.update(nonExistingId, entity);
		});
	}

	@Test
	public void findByIdShouldReturnProductDTOWhenIdExist() {
		ProductDTO entity = service.findById(existingId);

		Assertions.assertNotNull(entity);
	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}

	@Test
	public void findAllShouldReturnPage() {
		Pageable pageable = PageRequest.of(0, 12);

		Page<ProductDTO> result = service.findAllPaged(pageable);

		Assertions.assertNotNull(result);
		Mockito.verify(repository, times(1)).findAll(pageable);
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
	}

	@Test
	public void deleteShouldThrowDataIntegrityViolationExceptionWhenIdViolation() {
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});
	}
}
