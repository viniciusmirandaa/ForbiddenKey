package com.forbiddenkey.dto;

import java.io.Serializable;

import com.forbiddenkey.services.validation.UserUpdateValid;

@UserUpdateValid	
public class UserUpdateDTO extends UserDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String password;

	public UserUpdateDTO(Long id, String firstName, String lastName, String email, String password) {
		super(id, firstName, lastName, email);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
