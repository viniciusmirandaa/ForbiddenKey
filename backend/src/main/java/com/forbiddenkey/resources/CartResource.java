package com.forbiddenkey.resources;

import com.forbiddenkey.dto.cart.CartDTO;
import com.forbiddenkey.dto.customer.CustomerDTO;
import com.forbiddenkey.repositories.CartRepository;
import com.forbiddenkey.services.CartService;
import com.forbiddenkey.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/carts")
public class CartResource {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<CartDTO> findCurrentCart() {
        var cart = cartService.findCurrentCart(customerService.currentCustomerLogged());
        var dto = new CartDTO(cart, cart.getProducts());
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<CartDTO> insert(@PathVariable Long id) {
        var customerDTO = new CustomerDTO(customerService.currentCustomerLogged());
        var cartDTO = cartService.insert(id, customerDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cartDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CartDTO> update(@PathVariable Long id) {
        var cartDTO =
                cartService.update(id, cartService.findCurrentCart
                        (customerService.currentCustomerLogged()));
        return ResponseEntity.ok().body(cartDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CartDTO> removeItem(@PathVariable Long id) {
        var cartDTO =
                cartService.removeItem
                        (cartService.findCurrentCart(customerService.currentCustomerLogged())
                                , id);

        return ResponseEntity.noContent().build();
    }
}
