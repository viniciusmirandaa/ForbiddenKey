package com.forbiddenkey.resources;

import com.forbiddenkey.dto.CartDTO;
import com.forbiddenkey.dto.CustomerDTO;
import com.forbiddenkey.dto.ProductDTO;
import com.forbiddenkey.services.CartService;
import com.forbiddenkey.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/carts")
public class CartResource {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CartDTO> insert(@PathVariable Long id) {
        var customerDTO = new CustomerDTO(customerService.currentCustomerLogged());
        var cartDTO = cartService.insert(id, customerDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cartDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
