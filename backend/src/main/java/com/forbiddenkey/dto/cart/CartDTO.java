package com.forbiddenkey.dto.cart;

import com.forbiddenkey.dto.customer.CustomerDTO;
import com.forbiddenkey.dto.product.ProductDTO;
import com.forbiddenkey.entities.Cart;
import com.forbiddenkey.entities.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CartDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private CustomerDTO customerDTO;

    private List<ProductDTO> productsDTO = new ArrayList<>();

    private Boolean currentCart;

    private Double totalValue;

    private Double descontValue;

    public CartDTO(Cart entity, Set<Product> list) {
        this(entity);
        list.forEach(Product -> this.productsDTO.add(new ProductDTO(Product)));
    }

    public CartDTO(Cart entity) {
        this.id = entity.getId();
        this.customerDTO = new CustomerDTO(entity.getCustomer(), entity.getCustomer().getCards());
        this.currentCart = entity.getCurrentCart();
        this.totalValue = entity.getTotalValue();
        this.descontValue = entity.getDiscountValue();
    }

    public CartDTO(Long id, CustomerDTO customerDTO, Boolean currentCart, Double totalValue, Double descontValue) {
        this.id = id;
        this.customerDTO = customerDTO;
        this.currentCart = currentCart;
        this.totalValue = totalValue;
        this.descontValue = descontValue;
    }

    public Double getDescontValue() {
        return descontValue;
    }

    public void setDescontValue(Double descontValue) {
        this.descontValue = descontValue;
    }

    public CartDTO() {
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
        return productsDTO;
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
