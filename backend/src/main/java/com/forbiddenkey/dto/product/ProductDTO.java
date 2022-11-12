package com.forbiddenkey.dto.product;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.*;

import com.forbiddenkey.dto.category.CategoryDTO;
import com.forbiddenkey.dto.developer.DeveloperDTO;
import com.forbiddenkey.dto.distributor.DistributorDTO;
import com.forbiddenkey.entities.Category;
import com.forbiddenkey.entities.Product;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@Size(min = 5, max = 60, message = "Nome deve ter entre 5 e 60 caracteres.")
	@NotBlank(message = "Campo obrigatório.")
	private String name;
	@NotNull
	private int quantity;
	@NotNull
	private String description;
	@NotNull
	private DistributorDTO distributorDTO;
	@NotNull
	private DeveloperDTO developerDTO;
	@Positive(message = "Digite um valor positivo.")
	private Double price;
	@NotNull
	@PastOrPresent(message = "A data do produto não pode ser futura.")
	private Instant launchDate;
	@NotNull
	private String imgUrl;

	private List<CategoryDTO> categories = new ArrayList<>();

	public ProductDTO() {

	}

	public ProductDTO(Long id, String name, int quantity, String description, DistributorDTO distributorDTO, DeveloperDTO developerDTO, Double price, Instant launchDate, String imgUrl) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.distributorDTO = distributorDTO;
		this.developerDTO = developerDTO;
		this.price = price;
		this.launchDate = launchDate;
		this.imgUrl = imgUrl;
	}

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.quantity = entity.getQuantity();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.launchDate = entity.getLaunchDate();
		this.imgUrl = entity.getImgUrl();
		this.developerDTO = new DeveloperDTO(entity.getDeveloper());
		this.distributorDTO = new DistributorDTO(entity.getDistributor());
	}

	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DistributorDTO getDistributorDTO() {
		return distributorDTO;
	}

	public void setDistributorDTO(DistributorDTO distributorDTO) {
		this.distributorDTO = distributorDTO;
	}

	public DeveloperDTO getDeveloperDTO() {
		return developerDTO;
	}

	public void setDeveloperDTO(DeveloperDTO developerDTO) {
		this.developerDTO = developerDTO;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Instant getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Instant launchDate) {
		this.launchDate = launchDate;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}
}
