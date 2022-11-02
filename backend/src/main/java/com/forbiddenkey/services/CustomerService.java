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
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

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
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        var user = userRepository.findByEmail(email);
        Optional<Customer> obj = customerRepository.findByUser(user.getId());
        return obj.orElseThrow(() -> new ResourceNotFoundException("No user authenticated."));
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
