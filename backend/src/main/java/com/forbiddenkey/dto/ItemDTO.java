package com.forbiddenkey.dto;

import com.forbiddenkey.entities.Cart;
import com.forbiddenkey.entities.Item;
import com.forbiddenkey.entities.Product;

import java.io.Serializable;

public class ItemDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long cart;

    private Long product;

    public ItemDTO(Long id, Long cart, Long product) {
        this.id = id;
        this.cart = cart;
        this.product = product;
    }

    public ItemDTO(Item entity){
        this.id = entity.getId();
        this.cart = entity.getCart().getId();
        this.product = entity.getProduct().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCart() {
        return cart;
    }

    public void setCart(Long cart) {
        this.cart = cart;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }
}
