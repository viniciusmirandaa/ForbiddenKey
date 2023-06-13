package com.forbiddenkey.services;

import com.forbiddenkey.dto.customer.CustomerDTO;
import com.forbiddenkey.entities.Customer;
import com.forbiddenkey.entities.User;
import com.forbiddenkey.repositories.CustomerRepository;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerService {

    private static final String RESOURCE_NOT_FOUND_MESSAGE = "No user authenticated.";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAll(Pageable pageable) {
        Page<Customer> page = customerRepository.findAll(pageable);
        return page.map(CustomerDTO::new);
    }

    @Transactional
    public CustomerDTO insert(User user) {
        var customer = new Customer();
        customer.setUser(user);
        customer = customerRepository.save(customer);
        return new CustomerDTO(customer);
    }

    @Transactional
    public Customer currentCustomerLogged() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            var user = userService.currentUserLogged(email);
            Optional<Customer> customerObj = customerRepository.findByUser(user.getId());
            return customerObj.orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE));
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE);
        }
    }

    @Transactional
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
