package com.forbiddenkey.services;

import com.forbiddenkey.dto.CartDTO;
import com.forbiddenkey.dto.CategoryDTO;
import com.forbiddenkey.dto.CustomerDTO;
import com.forbiddenkey.dto.ProductDTO;
import com.forbiddenkey.entities.*;
import com.forbiddenkey.repositories.CartRepository;
import com.forbiddenkey.repositories.CustomerRepository;
import com.forbiddenkey.repositories.ItemRepository;
import com.forbiddenkey.repositories.ProductRepository;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public Cart findCurrentCart(Customer customer) {
        return cartRepository.findByCustomerIdAndCurrentCartTrue(customer.getId());
    }

    @Transactional
    public CartDTO insert(Long id, CustomerDTO customerDTO) {

        var item = new Item();

        Optional<Product> obj = productRepository.findById(id);
        var product = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));
        item.setProduct(product);
        item = itemRepository.save(item);

        var cart = new Cart(customerRepository.findById(customerDTO.getId()).get(), true);
        cart = cartRepository.save(cart);
        item.setCart(cart);
        itemRepository.save(item);
        cart = cartRepository.save(cart);

        return new CartDTO(cart, cart.getItems());
    }

    @Transactional
    public CartDTO update(Long productId, Cart cart) {

        var item = new Item(cart, productRepository.findById(productId).get());
        itemRepository.save(item);

        return new CartDTO(cart, cart.getItems());
    }

    public CartDTO removeItem(Cart currentCart, Long id) {
        try {
            var item = itemRepository.findByCartAndProduct(currentCart.getId(), id);
            itemRepository.deleteById(item.getId());
            var cart = cartRepository.findById(currentCart.getId()).get();
            return new CartDTO(cart, cart.getItems());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id {" + id + "} not found.");
        }
    }

    public CartDTO cartTotalValue(CartDTO cartDTO) {
        double totalValue = 0d;
        var cart = cartRepository.findByIdAndCurrentCartTrue(cartDTO.getId());
        for (Item i : cart.getItems()) {
            totalValue += i.getProduct().getPrice();
        }
        cart.setTotalValue(totalValue);
        cart = cartRepository.save(cart);

        return new CartDTO(cart, cart.getItems());
    }
}
