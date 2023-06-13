package com.forbiddenkey.components;

import com.forbiddenkey.entities.Cart;
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

@Component
public class TemporaryCart {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @Scheduled(fixedRate = 5000)
    public void verifyCartActualDate() {
        try {
            var carts = cartService.findAllCarts();
            for (Cart cart: carts){
                cart.setExpirationDate(Instant.now().minus(Duration.ofMillis(20000)));
                var value = cart.getExpirationDate();
            }

            System.out.println("as");
//            var cart = cartService.findCurrentCart(customerService.currentCustomerLogged());

//            cart.setExpirationDate(Instant.now().minus(Duration.ofMillis(20000)));
//            System.out.println("O carrinho possui agora menos 20 segundos de vida, restando apenas:"
//                    + cart.getExpirationDate());

        } catch (ResourceNotFoundException e) {
            return;
        }
    }
}
