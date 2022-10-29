package com.forbiddenkey.resources.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	public void addError(String field, String message) {
		this.getErrors().add(new FieldMessage(field, message));
	}
		
}
