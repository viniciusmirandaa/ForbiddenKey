package com.forbiddenkey.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.*;

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
	private String description;
	@Positive(message = "Digite um valor positivo.")
	private Double price;
	private DeveloperDTO developer;
	private DistributorDTO distributor;
	@PastOrPresent(message = "A data do produto não pode ser futura.")
	private Instant launchDate;
	private String imgUrl;

	private List<CategoryDTO> categories = new ArrayList<>();

	public ProductDTO() {

	}

	public ProductDTO(Long id, String name, int quantity, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.quantity = entity.getQuantity();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.developer = new DeveloperDTO(entity.getDeveloper());
		this.distributor = new DistributorDTO(entity.getDistributor());
		this.launchDate = entity.getLaunchDate();
		this.imgUrl = entity.getImgUrl();
	}

	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
	}

	public DeveloperDTO getDeveloper() {
		return developer;
	}

	public void setDeveloper(DeveloperDTO developer) {
		this.developer = developer;
	}

	public DistributorDTO getDistributor() {
		return distributor;
	}

	public void setDistributor(DistributorDTO distributor) {
		this.distributor = distributor;
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
