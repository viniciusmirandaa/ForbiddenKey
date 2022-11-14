package com.forbiddenkey.entities;

import com.forbiddenkey.entities.Enum.OrderStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_order")
public class Order extends DomainEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String protocol;

    public Order() {
    }

    public Order(Customer customer, Cart cart, OrderStatus status, String protocol) {
        this.customer = customer;
        this.cart = cart;
        this.status = status;
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
