package com.forbiddenkey.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.forbiddenkey.entities.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String ERROR_MESSAGE = "Por favor, preencha com dados válidos todos os campos obrigatórios abaixo.";

	private Long id;
	@NotBlank(message = ERROR_MESSAGE)
	private String firstName;
	@NotBlank(message = ERROR_MESSAGE)
	private String lastName;
	@Email(message = ERROR_MESSAGE)
	private String email;

	private Set<RoleDTO> roles = new HashSet<>();

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public UserDTO(Long id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.email = email;
	}

	public UserDTO(User entity) {
		this.id = entity.getId();
		this.email = entity.getEmail();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
