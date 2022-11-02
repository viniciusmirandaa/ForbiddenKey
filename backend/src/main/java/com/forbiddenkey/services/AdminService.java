package com.forbiddenkey.services;

import com.forbiddenkey.dto.AdminDTO;
import com.forbiddenkey.dto.CustomerDTO;
import com.forbiddenkey.entities.Admin;
import com.forbiddenkey.entities.Customer;
import com.forbiddenkey.entities.User;
import com.forbiddenkey.repositories.AdminRepository;
import com.forbiddenkey.repositories.UserRepository;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    public AdminDTO insert(User user) {
        var admin = new Admin();
        admin.setUser(user);
        admin = adminRepository.save(admin);
        return new AdminDTO(admin);
    }

    public Admin currentCustomerLogged() throws ResourceNotFoundException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (email != null) {
            var user = userRepository.findByEmail(email);
            return adminRepository.findByUser(user.getId());
        }
        throw new ResourceNotFoundException("No user found.");
    }
}
