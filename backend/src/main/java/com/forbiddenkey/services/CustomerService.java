package com.forbiddenkey.services;

import com.forbiddenkey.dto.CustomerDTO;
import com.forbiddenkey.dto.UserInsertDTO;
import com.forbiddenkey.entities.Customer;
import com.forbiddenkey.entities.User;
import com.forbiddenkey.repositories.CustomerRepository;
import com.forbiddenkey.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public CustomerDTO insert(User user) {
        var customer = new Customer();
        customer.setUser(user);
        customer = customerRepository.save(customer);
        return new CustomerDTO(customer);
    }

    public CustomerDTO currentCustomerLogged(){
        String principal = SecurityContextHolder.getContext().getAuthentication().getName();

        if (principal != null) {
            var user = userRepository.findByEmail(principal);
            var customer = customerRepository.findByUser(user.getId());
            return new CustomerDTO(customer);
        }
        return new CustomerDTO();
    }
}
