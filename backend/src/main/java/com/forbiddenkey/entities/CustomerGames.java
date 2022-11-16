package com.forbiddenkey.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_customer_games")
public class CustomerGames extends DomainEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String activationKey;

    public CustomerGames() {
    }

    public CustomerGames(Order order, Customer customer, String activationKey) {
        this.order = order;
        this.customer = customer;
        this.activationKey = activationKey;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
