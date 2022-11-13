package com.forbiddenkey.entities;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_cart")
public class Cart extends DomainEntity {

    @ManyToOne()
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_cart_product", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    private Boolean currentCart;

    private Double totalValue;

    public Cart() {
    }

    public Cart(Customer customer, Boolean currentCart) {
        this.customer = customer;
        this.currentCart = currentCart;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
