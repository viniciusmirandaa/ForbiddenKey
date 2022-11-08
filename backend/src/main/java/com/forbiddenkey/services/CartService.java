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
    public CartDTO findById(Long id) {
        try {
            var cart = cartRepository.findByIdAndCurrentCartTrue(id);
            return new CartDTO(cart, cart.getItems());
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Id {" + id + "} not found.");
        }
    }


    @Transactional
    public CartDTO insert(Long id, CustomerDTO customerDTO) {

        var item = new Item();
        var cart = new Cart();

        Optional<Product> obj = productRepository.findById(id);
        var product = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));
        item.setProduct(product);
        item = itemRepository.saveAndFlush(item);

        cart.setCustomer(customerRepository.findById(customerDTO.getId()).get());
        cart.setCurrentCart(true);
        cart = cartRepository.saveAndFlush(cart);
        item.setCart(cart);
        itemRepository.saveAndFlush(item);
        cart = cartRepository.saveAndFlush(cart);

        return new CartDTO(cart, cart.getItems());
    }

//    public Cart update(Cart cart){
//
//        Item item = cart.getItems().get(0);
//
//        Long item_quant = item.getQuantity();
//
//        Long customer_id = cart.getCustomer().getId();
//
//        Cart currentCart = getCartMethod(cart);
//
//        item.setCart(currentCart);
//
//        Long cart_id = currentCart.getId();
//
//        Long product_id = item.getProduct().getId();
//
//        Item currentItem = itemRepository.findByCartAndProduct(cart_id, product_id);
//
//        if(currentItem == null){
//            this.setItemQuantityMethod(currentItem, item.getQuantity());
//            itemRepository.save(item);
//        }
//        else{
//            this.setItemQuantityMethod(currentItem, item.getQuantity());
//            itemRepository.save(currentItem);
//        }
//
//        this.updateTotalValueMethod(currentCart);
//
//        return cartRepository.save(currentCart);
//    }
//
//    private void setItemQuantityMethod(Item item, Long quantity){
//
//        Optional<Product> obj = productRepository.findById(item.getProduct().getId());
//        var product = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + item.getProduct().getId() + "} not found."));
//
//        Double value = product.getPrice();
//
//        item.setTotalValue(value * quantity);
//
//        item.setQuantity(quantity);
//    }
//
//    private void updateTotalValueMethod(Cart cart){
//
//        List<Item> items = itemRepository.findAllByCart(cart);
//
//        Double totalValue = 0.0;
//
//        for(Item item : items){
//            totalValue += item.getTotalValue();
//        }
//
//        cart.setTotalValue(totalValue);
//    }
//
//    private Cart getCartMethod(Cart cart){
//
//        Long customer_id = cart.getCustomer().getId();
//
//        Cart currentCart = cartRepository.findByCustomerIdAndCurrentCartTrue(customer_id);
//
//        if(currentCart == null){this.insert(cart);}
//
//        return currentCart;
//    }
//
//    public void deleteItemMethod(Long id){
//        itemRepository.deleteById(id);
//    }
//
//    public List<DomainEntity> readCartMethod(DomainEntity domainEntity){
//
//        Cart cart = ( Cart ) domainEntity;
//
//        Long customer_id = cart.getCustomer().getId();
//
//        Cart currentCart = getCartMethod(cart);
//
//        List<Item> items = itemRepository.findAllByCart(currentCart);
//
//        List<DomainEntity> result = new ArrayList<>();
//
//        for(Item item : items){
//            result.add(item);
//        }
//
//        return result;
//    }

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
