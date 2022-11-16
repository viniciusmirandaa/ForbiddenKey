package com.forbiddenkey.dto.customerGames;

import com.forbiddenkey.dto.customer.CustomerDTO;
import com.forbiddenkey.dto.order.OrderDTO;
import com.forbiddenkey.entities.CustomerGames;

import java.io.Serializable;

public class CustomerGamesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private OrderDTO orderDTO;

    private CustomerDTO customerDTO;

    private String activationKey;

    public CustomerGamesDTO() {
    }

    public CustomerGamesDTO(OrderDTO orderDTO, CustomerDTO customerDTO, String activationKey) {
        this.orderDTO = orderDTO;
        this.customerDTO = customerDTO;
        this.activationKey = activationKey;
    }

    public CustomerGamesDTO(CustomerGames entity) {
        this.orderDTO = new OrderDTO(entity.getOrder());
        this.customerDTO = new CustomerDTO(entity.getCustomer());
        this.activationKey = entity.getActivationKey();
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }
}
