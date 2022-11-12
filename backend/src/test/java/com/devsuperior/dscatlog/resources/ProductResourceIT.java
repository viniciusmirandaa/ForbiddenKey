package com.devsuperior.dscatlog.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatlog.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forbiddenkey.dto.product.ProductDTO;
import com.forbiddenkey.repositories.ProductRepository;
import com.forbiddenkey.services.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductResourceIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductService service;

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	private long existingId;
	private long nonExistingId;
	private long countTotalProducts;
	private ResultActions result;
	private ProductDTO productDTO;
	private String jsonBody;

	@BeforeEach
	void setup() throws Exception {

		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
		productDTO = Factory.createProductDTO();
		jsonBody = objectMapper.writeValueAsString(productDTO);
	}

	@Test
	public void findAllShouldReturnSortedPageWhenSortByName() throws Exception {

		result = mockMvc.perform(get("/products?page=0&size=10&sort=name,asc").accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.totalElements").value(countTotalProducts));
		result.andExpect(jsonPath("$.numberOfElements").value(10));
		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.sort.sorted").value(true));
		result.andExpect(jsonPath("$.content[0].name").value("Macbook Pro"));
	}

	@Test
	public void updateShouldReturnProductDTOWhenIdExists() throws Exception {

		String expectedName = productDTO.getName();
		String expectedDescription = productDTO.getDescription();
		Double expectedPrice = productDTO.getPrice();

		result = mockMvc.perform(put("/products/{id}", existingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.name").value(expectedName));
		result.andExpect(jsonPath("$.description").value(expectedDescription));
		result.andExpect(jsonPath("$.price").value(expectedPrice));
	}

	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() throws Exception {

		result = mockMvc.perform(put("/product/{id}", nonExistingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
		result.andExpect(jsonPath("$.id").doesNotExist());
	}
}
