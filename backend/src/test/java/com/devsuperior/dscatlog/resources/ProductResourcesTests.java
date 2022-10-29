package com.devsuperior.dscatlog.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.devsuperior.dscatlog.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forbiddenkey.dto.ProductDTO;
import com.forbiddenkey.resources.ProductResource;
import com.forbiddenkey.services.ProductService;
import com.forbiddenkey.services.exceptions.DatabaseException;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;

@WebMvcTest(ProductResource.class)
public class ProductResourcesTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Autowired
	private ObjectMapper objectMapper;

	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	private ProductDTO productDTO;
	private PageImpl<ProductDTO> page;
	private ResultActions result;
	private String jsonBody;

	@BeforeEach
	void setup() throws Exception {

		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		productDTO = Factory.createProductDTO();
		jsonBody = objectMapper.writeValueAsString(productDTO);
		page = new PageImpl<>(List.of(productDTO));

		when(productService.findAllPaged(any())).thenReturn(page);

		when(productService.update(eq(existingId), any())).thenReturn(productDTO);
		when(productService.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);

		when(productService.findById(existingId)).thenReturn(productDTO);
		when(productService.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

		when(productService.insert(any())).thenReturn(productDTO);

		doNothing().when(productService).delete(existingId);
		doThrow(ResourceNotFoundException.class).when(productService).delete(nonExistingId);
		doThrow(DatabaseException.class).when(productService).delete(dependentId);
	}

	@Test
	public void insertShouldCreateProduct() throws Exception {

		result = mockMvc.perform(post("/products").content(jsonBody).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
	}

	@Test
	public void deleteShouldDeleteWhenIdExists() throws Exception {

		result = mockMvc.perform(delete("/products/{id}", existingId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNoContent());
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() throws Exception {

		result = mockMvc.perform(delete("/products/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}

	@Test
	public void deleteShouldThrowDataIntegrityViolationExceptionWhenIdDependent() throws Exception {

		result = mockMvc.perform(delete("/products/{id}", dependentId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isBadRequest());
	}

	@Test
	public void updateShouldReturnProductDTOWhenIdExists() throws Exception {

		result = mockMvc.perform(put("/products/{id}", existingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
	}

	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		result = mockMvc.perform(put("/products/{id}", nonExistingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
		result.andExpect(jsonPath("$.id").doesNotExist());
	}

	@Test
	public void findAllShouldReturnPage() throws Exception {

		result = mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
	}

	@Test
	public void findByIdShouldReturnProductDTOWhenIdExists() throws Exception {
		result = mockMvc.perform(get("/products/{id}", existingId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
	}

	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		result = mockMvc.perform(get("/product/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}
}
