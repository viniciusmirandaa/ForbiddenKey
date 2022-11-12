package com.forbiddenkey.services;

import com.forbiddenkey.dto.cart.CartDTO;
import com.forbiddenkey.dto.customer.CustomerDTO;
import com.forbiddenkey.entities.*;
import com.forbiddenkey.repositories.CartRepository;
import com.forbiddenkey.repositories.CustomerRepository;
import com.forbiddenkey.repositories.ProductRepository;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public Cart findCurrentCart(Customer customer) {
        return cartRepository.findByCustomerIdAndCurrentCartTrue(customer.getId());
    }

    @Transactional
    public CartDTO insert(Long id, CustomerDTO customerDTO) {

        Optional<Product> obj = productRepository.findById(id);
        var product = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));

        var cart = new Cart(customerRepository.findById(customerDTO.getId()).get(), true);
        cart.getProducts().add(product);
        cartTotalValue(cart);

        return new CartDTO(cart, cart.getProducts());
    }

    @Transactional
    public CartDTO update(Long productId, Cart cart) {

        Optional<Product> obj = productRepository.findById(productId);
        var product = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + productId + "} not found."));
        cart.getProducts().add(product);
        cartTotalValue(cart);

        return new CartDTO(cart, cart.getProducts());
    }

    public CartDTO removeItem(Cart currentCart, Long id) {
        try {
            Optional<Product> obj = productRepository.findById(id);
            var product = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));
            currentCart.getProducts().remove(product);
            cartTotalValue(currentCart);
            return new CartDTO(currentCart, currentCart.getProducts());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id {" + id + "} not found.");
        }
    }

    public void cartTotalValue(Cart cart) {
        double totalValue = 0d;
        for (Product i : cart.getProducts()) {
            totalValue += i.getPrice();
        }
        cart.setTotalValue(totalValue);
        cartRepository.save(cart);
    }
}
