package com.forbiddenkey.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_category")
public class Category extends DomainEntity {
	private static final long serialVersionUID = 1L;


	private String name;

	@ManyToMany(mappedBy = "categories")
	private Set<Product> product = new HashSet<>();

	public Category() {

	}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProduct() {
		return product;
	}
}
