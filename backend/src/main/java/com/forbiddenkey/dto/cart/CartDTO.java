package com.forbiddenkey.dto.cart;

import com.forbiddenkey.dto.customer.CustomerDTO;
import com.forbiddenkey.dto.product.ProductDTO;
import com.forbiddenkey.entities.Cart;
import com.forbiddenkey.entities.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private CustomerDTO customerDTO;

    private List<ProductDTO> products = new ArrayList<>();

    private Boolean currentCart;

    private Double totalValue;

    public CartDTO(Cart entity, List<Product> list) {
        this(entity);
        list.forEach(Product -> this.products.add(new ProductDTO(Product)));
    }

    public CartDTO(Cart entity) {
        this.id = entity.getId();
        this.customerDTO = new CustomerDTO(entity.getCustomer());
        this.currentCart = entity.getCurrentCart();
        this.totalValue = entity.getTotalValue();
    }

    public CartDTO(Long id, Long customer, Boolean currentCart, Double totalValue) {
        this.id = id;
        this.currentCart = currentCart;
        this.totalValue = totalValue;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductDTO> getProducts() {
        return products;
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
