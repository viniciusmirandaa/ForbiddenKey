package com.forbiddenkey.resources;

import com.forbiddenkey.dto.customer.CustomerDTO;
import com.forbiddenkey.dto.user.UserDTO;
import com.forbiddenkey.dto.user.UserInsertDTO;
import com.forbiddenkey.dto.user.UserUpdateDTO;
import com.forbiddenkey.repositories.RoleRepository;
import com.forbiddenkey.services.CustomerService;
import com.forbiddenkey.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
        var customerDTO = new CustomerDTO(customer, customer.getCards());
        return ResponseEntity.ok().body(customerDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDTO>> findAll(Pageable pageable){
        var customerDto = customerService.findAll(pageable);
        return ResponseEntity.ok().body(customerDto);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> insert(@Valid @RequestBody UserInsertDTO dto) {
        var role = roleRepository.findByRole("ROLE_CUSTOMER");
        var customerDTO = customerService.insert(userService.insert(dto, role));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customerDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> update(@Valid @RequestBody CustomerDTO dto){
        var entity = customerService.update(dto);
        return ResponseEntity.ok().body(entity);
    }

    @PutMapping(value = "/password")
    public ResponseEntity<UserDTO> updatePassword(@Valid @RequestBody UserUpdateDTO dto){
        var entity = userService.update(customerService.currentCustomerLogged().getUser().getId(), dto);
        return ResponseEntity.ok().body(entity);
    }
}
