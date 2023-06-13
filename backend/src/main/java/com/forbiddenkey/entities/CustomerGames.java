package com.forbiddenkey.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_customer_games")
public class CustomerGames extends DomainEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String activationKey;

    private boolean isSeen;

    public CustomerGames() {
    }

    public CustomerGames(Product product, Customer customer, Order order, String activationKey, boolean isSeen) {
        this.product = product;
        this.customer = customer;
        this.order = order;
        this.activationKey = activationKey;
        this.isSeen = isSeen;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
