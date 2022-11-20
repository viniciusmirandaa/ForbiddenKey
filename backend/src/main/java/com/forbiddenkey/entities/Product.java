package com.forbiddenkey.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_product")
public class Product extends DomainEntity {
    private static final long serialVersionUID = 1L;

    private String name;
    private int quantity;
    private int selledQuantity;
    @Column(columnDefinition = "TEXT")
    private String description;
    private double price;
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;
    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;
    private Instant launchDate;
    private String imgUrl;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();
    @ManyToMany(mappedBy = "products")
    private Set<Cart> carts = new HashSet<>();
    @OneToMany(mappedBy = "product")
    private List<CustomerGames> customerGames = new ArrayList<>();

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, int quantity, int selledQuantity, String description, double price, Developer developer,
                   Distributor distributor, Instant launchDate, String imgUrl, boolean active) {
        this.name = name;
        this.quantity = quantity;
        this.selledQuantity = selledQuantity;
        this.description = description;
        this.price = price;
        this.developer = developer;
        this.distributor = distributor;
        this.launchDate = launchDate;
        this.imgUrl = imgUrl;
        this.active = active;
    }

    public List<CustomerGames> getCustomerGames() {
        return customerGames;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public int getSelledQuantity() {
        return selledQuantity;
    }

    public void setSelledQuantity(int selledQuantity) {
        this.selledQuantity = selledQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public Instant getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Instant launchDate) {
        this.launchDate = launchDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
