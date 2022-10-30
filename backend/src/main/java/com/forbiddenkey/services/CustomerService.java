package com.forbiddenkey.services;

import com.forbiddenkey.dto.CustomerDTO;
import com.forbiddenkey.entities.Customer;
import com.forbiddenkey.entities.User;
import com.forbiddenkey.repositories.CustomerRepository;
import com.forbiddenkey.repositories.UserRepository;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<CustomerDTO> findAll(Pageable pageable) {
        Page<Customer> page = customerRepository.findAll(pageable);
        return page.map(CustomerDTO::new);
    }

    public CustomerDTO insert(User user) {
        var customer = new Customer();
        customer.setUser(user);
        customer = customerRepository.save(customer);
        return new CustomerDTO(customer);
    }

    public Customer currentCustomerLogged() throws ResourceNotFoundException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (email != null) {
            var user = userRepository.findByEmail(email);
            return customerRepository.findByUser(user.getId());
        }
        throw new ResourceNotFoundException("No user found.");
    }

    public CustomerDTO update(CustomerDTO dto) {
        var customer = currentCustomerLogged();
        copyDtoToEntity(dto, customer);
        customer = customerRepository.save(customer);
        return new CustomerDTO(customer);
    }

    public void copyDtoToEntity(CustomerDTO dto, Customer entity) {
        entity.setPhone(dto.getPhone());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
    }
}
