package com.forbiddenkey.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_developer")
public class Developer extends DomainEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	@OneToMany(mappedBy = "developer")
	private List<Product> developerProduct = new ArrayList<>();

	public Developer() {

	}

	public Developer(String name) {
		this.name = name;
	}

	public List<Product> getDeveloperProduct() {
		return developerProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
