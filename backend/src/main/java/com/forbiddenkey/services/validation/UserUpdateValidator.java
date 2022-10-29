package com.forbiddenkey.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.forbiddenkey.dto.UserUpdateDTO;
import com.forbiddenkey.repositories.UserRepository;
import com.forbiddenkey.resources.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long userId = Long.parseLong(uriVars.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		var user = repository.findByEmail(dto.getEmail());
		
		if (user != null && user.getId() != userId) {
			list.add(new FieldMessage("email", "o email " + dto.getEmail() +" já é existente."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
