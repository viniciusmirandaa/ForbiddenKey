package com.forbiddenkey.dto;

import com.forbiddenkey.entities.Cart;
import com.forbiddenkey.entities.Customer;
import com.forbiddenkey.entities.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long customer;

    private List<ItemDTO> items = new ArrayList<>();

    private Boolean currentCart;

    private Double totalValue;

    public CartDTO(Cart entity, List<Item> list) {
        this(entity);
        list.forEach(item -> this.items.add(new ItemDTO(item)));
    }

    public CartDTO(Cart entity) {
        this.id = entity.getId();
        this.customer = entity.getCustomer().getId();
        this.currentCart = entity.getCurrentCart();
        this.totalValue = entity.getTotalValue();
    }

    public CartDTO(Long id, Long customer, Boolean currentCart, Double totalValue) {
        this.id = id;
        this.customer = customer;
        this.currentCart = currentCart;
        this.totalValue = totalValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public Boolean getCurrentCart() {
        return currentCart;
    }

    public void setCurrentCart(Boolean currentCart) {
        this.currentCart = currentCart;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
