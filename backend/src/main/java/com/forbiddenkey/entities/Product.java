package com.forbiddenkey.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_product")
public class Product extends DomainEntity {
    private static final long serialVersionUID = 1L;

    private String name;
    private int quantity;
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();
    @ManyToMany(mappedBy = "products")
    private Set<Cart> carts = new HashSet<>();

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, String description, double price, Developer developer, Distributor distributor,
                   Instant launchDate, String imgUrl, int quantity) {
        super();
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.developer = developer;
        this.distributor = distributor;
        this.launchDate = launchDate;
        this.imgUrl = imgUrl;
    }

    public Set<Cart> getCarts() {
        return carts;
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

}
