package com.forbiddenkey.services;

import com.forbiddenkey.dto.admin.AdminDTO;
import com.forbiddenkey.entities.Admin;
import com.forbiddenkey.entities.User;
import com.forbiddenkey.repositories.AdminRepository;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminService {

    private static final String RESOURCE_NOT_FOUND_MESSAGE = "No user authenticated.";

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public AdminDTO insert(User user) {
        var admin = new Admin();
        admin.setUser(user);
        admin = adminRepository.save(admin);
        return new AdminDTO(admin);
    }

    @Transactional
    public Admin currentAdminLogged() throws ResourceNotFoundException {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            var user = userService.currentUserLogged(email);
            Optional<Admin> customerObj = adminRepository.findByUser(user.getId());
            return customerObj.orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE));
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE);
        }
    }
}
