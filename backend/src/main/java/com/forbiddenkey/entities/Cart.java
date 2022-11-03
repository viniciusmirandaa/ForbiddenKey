package com.forbiddenkey.entities;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_cart")
public class Cart extends DomainEntity {

    @ManyToOne()
    @JoinColumn(name = "customer")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private List<Item> items;

    private Boolean currentCart;

    private Double totalValue;

    public Cart() {
    }

    public Cart(Customer customer, List<Item> items, Boolean currentCart, Double totalValue) {
        this.customer = customer;
        this.items = items;
        this.currentCart = currentCart;
        this.totalValue = totalValue;
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

    public List<Item> getItems() {
        return items;
    }

}
