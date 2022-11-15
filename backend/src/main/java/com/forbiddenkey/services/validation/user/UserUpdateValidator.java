package com.forbiddenkey.services.validation.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.forbiddenkey.services.AdminService;
import com.forbiddenkey.services.CustomerService;
import com.forbiddenkey.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.forbiddenkey.dto.user.UserUpdateDTO;
import com.forbiddenkey.repositories.UserRepository;
import com.forbiddenkey.resources.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<FieldMessage> list = new ArrayList<>();

        var user = userService.currentUserLogged(email);

        if (!cryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())) {
            list.add(new FieldMessage("password", "A senha inserida não corresponde com a do usuário."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
