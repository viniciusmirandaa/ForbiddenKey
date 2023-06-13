package com.forbiddenkey.services;

import com.forbiddenkey.dto.cart.CartDTO;
import com.forbiddenkey.dto.customer.CustomerDTO;
import com.forbiddenkey.entities.*;
import com.forbiddenkey.repositories.*;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<Cart> findAllCarts() {
        return cartRepository.findAllByCartTrue();
    }

    @Transactional(readOnly = true)
    public Cart findCurrentCart(Customer customer) {
        Optional<Cart> obj = cartRepository.findByCustomerIdAndCurrentCartTrue(customer.getId());
        return obj.orElseThrow(() -> new ResourceNotFoundException("Cart not found."));
    }

    @Transactional
    public CartDTO insert(Long id, Customer customer) {

        Optional<Product> obj = productRepository.findById(id);
        var product = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));

        var cart = new Cart(customer, true);
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
            if (currentCart.getProducts().size() == 0) {
                currentCart.setDiscountValue(0d);
            }
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
        if (isBirthDay(cart.getCustomer())) {
            var discountValue = 25d;
            cart.setTotalValue((totalValue > 0) ? (totalValue - discountValue) : totalValue);
            cart.setDiscountValue(cart.getDiscountValue() + discountValue);
        } else cart.setTotalValue(totalValue);
        cart.setExpirationDate(120000);
        cartRepository.save(cart);
    }

    private boolean isBirthDay(Customer customer) {
        if (customer.getBirthDate() == null) return false;
        LocalDate localDate = LocalDate.now();
        int day = customer.getBirthDate().getDayOfMonth();
        Month month = customer.getBirthDate().getMonth();

        return day == localDate.getDayOfMonth() && month == localDate.getMonth();
    }
}
