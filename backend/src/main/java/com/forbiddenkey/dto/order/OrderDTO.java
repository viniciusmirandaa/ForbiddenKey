package com.forbiddenkey.dto.order;

import com.forbiddenkey.dto.card.CardDTO;
import com.forbiddenkey.dto.cart.CartDTO;
import com.forbiddenkey.entities.Card;
import com.forbiddenkey.entities.Enum.OrderStatus;
import com.forbiddenkey.entities.Order;

import java.io.Serializable;
import java.time.Instant;

import static org.springframework.data.util.CastUtils.cast;

public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private CartDTO cartDTO;

    private OrderStatus orderStatus;

    private String protocol;

    private CardDTO cardDTO;

    private Instant orderCreatDate;

    public CardDTO getCardDTO() {
        return cardDTO;
    }

    public void setCardDTO(CardDTO cardDTO) {
        this.cardDTO = cardDTO;
    }

    public Instant getOrderCreatDate() {
        return orderCreatDate;
    }

    public void setOrderCreatDate() {
        this.orderCreatDate = Instant.now().minusSeconds(10800);
    }

    public OrderDTO() {
    }

    public OrderDTO(Long id, CartDTO cartDTO, OrderStatus orderStatus, String protocol) {
        this.id = id;
        this.cartDTO = cartDTO;
        this.orderStatus = orderStatus;
        this.protocol = protocol;
        this.setOrderCreatDate();
    }

    public OrderDTO(Long id, OrderStatus orderStatus, String protocol) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.protocol = protocol;
        this.setOrderCreatDate();
    }

    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.cartDTO = new CartDTO(entity.getCart(), entity.getCart().getProducts());
        this.orderStatus = entity.getStatus();
        this.protocol = entity.getProtocol();
        this.cardDTO = new CardDTO(entity.getCard());
        this.setOrderCreatDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartDTO getCartDTO() {
        return cartDTO;
    }

    public void setCartDTO(CartDTO cartDTO) {
        this.cartDTO = cartDTO;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
