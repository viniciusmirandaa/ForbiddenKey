package com.forbiddenkey.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_item")
public class Item extends DomainEntity {

    @ManyToOne
    @JoinColumn(name = "cart")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    public Item(){}

    public Item(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
