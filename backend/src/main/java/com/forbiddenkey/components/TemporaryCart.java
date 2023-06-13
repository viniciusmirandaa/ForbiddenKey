package com.forbiddenkey.components;

import com.forbiddenkey.entities.Cart;
import com.forbiddenkey.entities.Product;
import com.forbiddenkey.repositories.CartRepository;
import com.forbiddenkey.services.CartService;
import com.forbiddenkey.services.CustomerService;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
public class TemporaryCart {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Scheduled(fixedRate = 60000)
    public void verifyCartActualDate() {
        try {
            for (Cart cart: cartService.findAllCarts()){
                if (cart.getProducts().size() != 0){
                    cart.setExpirationDate(cart.getExpirationDate() - 60000);
                    cartRepository.save(cart);
                }

                if (cart.getExpirationDate() == 0){
                    List<Product> productIterator = new ArrayList<>(cart.getProducts());
                    for (Product product: productIterator){
                        cartService.removeItem(cart, product.getId());
                    }
                    cartRepository.save(cart);
                }
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("Verificar o cron de expiração do carrinho.");
        }
    }
}
