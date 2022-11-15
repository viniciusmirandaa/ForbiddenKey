package com.forbiddenkey.dto.user;

import java.io.Serializable;

import com.forbiddenkey.services.validation.user.UserUpdateValid;

import javax.validation.constraints.NotBlank;

@UserUpdateValid	
public class UserUpdateDTO implements Serializable{

	private static final String ERROR_MESSAGE = "Por favor, preencha com dados válidos todos os campos obrigatórios abaixo.";
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = ERROR_MESSAGE)
	private String newPassword;
	@NotBlank(message = ERROR_MESSAGE)
	private String repeatedPassword;
	@NotBlank(message = ERROR_MESSAGE)
	private String password;

	public UserUpdateDTO(String newPassword, String repeatedPassword, String password) {
		this.newPassword = newPassword;
		this.repeatedPassword = repeatedPassword;
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
