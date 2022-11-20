package com.forbiddenkey.dto.customerGames;

import com.forbiddenkey.dto.customer.CustomerDTO;
import com.forbiddenkey.dto.order.OrderDTO;
import com.forbiddenkey.dto.product.ProductDTO;
import com.forbiddenkey.entities.CustomerGames;

import java.io.Serializable;

public class CustomerGamesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private ProductDTO productDTO;

    private String activationKey;

    private boolean isSeen;

    public CustomerGamesDTO() {
    }

    public CustomerGamesDTO(Long id, ProductDTO productDTO, String activationKey, boolean isSeen) {
        this.id = id;
        this.productDTO = productDTO;
        this.activationKey = activationKey;
        this.isSeen = isSeen;
    }

    public CustomerGamesDTO(CustomerGames entity) {
        this.id = entity.getId();
        this.productDTO = new ProductDTO(entity.getProduct());
        this.activationKey = entity.getActivationKey();
        this.isSeen = entity.isSeen();
    }

    public boolean isSeen() {
        return isSeen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }
}
