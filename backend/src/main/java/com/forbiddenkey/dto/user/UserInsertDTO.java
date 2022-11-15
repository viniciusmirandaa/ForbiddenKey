package com.forbiddenkey.dto.user;

import java.io.Serializable;

import com.forbiddenkey.services.validation.user.UserInsertValid;

import javax.validation.constraints.NotBlank;

@UserInsertValid
public class UserInsertDTO extends UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo senha obrigatório.")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserInsertDTO() {
		super();
	}
}
