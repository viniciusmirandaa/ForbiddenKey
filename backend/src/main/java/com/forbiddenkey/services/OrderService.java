package com.forbiddenkey.services;

import com.forbiddenkey.dto.order.OrderDTO;
import com.forbiddenkey.entities.Cart;
import com.forbiddenkey.entities.Customer;
import com.forbiddenkey.entities.Enum.OrderStatus;
import com.forbiddenkey.entities.Order;
import com.forbiddenkey.repositories.CartRepository;
import com.forbiddenkey.repositories.CustomerRepository;
import com.forbiddenkey.repositories.OrderRepository;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        return new OrderDTO(orderRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(Customer customer) {
        List<Order> orders = orderRepository.findByCustomerId(customer.getId());
        return orders.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(Long id) {
        Optional<Cart> obj = cartRepository.findById(id);
        var cart = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));
        cart.setCurrentCart(false);

        var order = new Order(cart.getCustomer(), cart, OrderStatus.EM_PROCESSAMENTO, createProtocol());
        cartRepository.save(cart);
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO update(OrderDTO orderDTO) {
        Optional<Order> obj = orderRepository.findById(orderDTO.getId());
        var entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + orderDTO.getId() + "} not found."));
        if (orderDTO.getOrderStatus() == OrderStatus.EM_PROCESSAMENTO) entity.setStatus(OrderStatus.CANCELADO);
        else entity.setStatus(OrderStatus.FINALIZADO);
        entity = orderRepository.save(entity);

        return new OrderDTO(entity);
    }

    private String createProtocol() {
        StringBuilder protocol = new StringBuilder();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = localDate.format(dateTimeFormatter);
        var lastId = orderRepository.findLastInserted();
        if (lastId == null) return protocol.append(date).append("1").toString();
        else return protocol.append(date).append(lastId + 1).toString();
    }
}
