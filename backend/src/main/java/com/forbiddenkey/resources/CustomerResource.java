package com.forbiddenkey.resources;

import com.forbiddenkey.dto.CustomerDTO;
import com.forbiddenkey.dto.UserDTO;
import com.forbiddenkey.dto.UserInsertDTO;
import com.forbiddenkey.entities.Role;
import com.forbiddenkey.repositories.RoleRepository;
import com.forbiddenkey.repositories.UserRepository;
import com.forbiddenkey.services.CustomerService;
import com.forbiddenkey.services.UserService;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/details")
    public ResponseEntity<CustomerDTO> findById(){
        var customer = customerService.currentCustomerLogged();
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> insert(@Valid @RequestBody UserInsertDTO dto) {
        Optional<Role> obj = roleRepository.findById(1L);
        var role = obj.get();
        var entity = userService.insert(dto, role);
        var customerDTO = customerService.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customerDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//    @PutMapping(value = "/{id}")
//    public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody CustomerDTO){
//
//    }


}
