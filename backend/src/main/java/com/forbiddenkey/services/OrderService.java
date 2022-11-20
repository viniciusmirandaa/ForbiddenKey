package com.forbiddenkey.services;

import com.forbiddenkey.dto.order.OrderDTO;
import com.forbiddenkey.entities.Cart;
import com.forbiddenkey.entities.Customer;
import com.forbiddenkey.entities.Enum.OrderStatus;
import com.forbiddenkey.entities.Order;
import com.forbiddenkey.entities.Product;
import com.forbiddenkey.repositories.CartRepository;
import com.forbiddenkey.repositories.CustomerRepository;
import com.forbiddenkey.repositories.OrderRepository;
import com.forbiddenkey.repositories.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

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
    public OrderDTO insert(Cart cart) {
        Optional<Cart> obj = cartRepository.findById(cart.getId());
        var entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + cart.getId() + "} not found."));
        entity.setCurrentCart(false);

        for (Product prod : entity.getProducts()) {
            prod.setQuantity(prod.getQuantity() - 1);
            productRepository.save(prod);
        }

        var order = new Order(entity.getCustomer(), entity, OrderStatus.EM_PROCESSAMENTO, createProtocol());

        cartRepository.save(entity);
        order = orderRepository.save(order);

        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO update(OrderDTO orderDTO) {
        Optional<Order> obj = orderRepository.findById(orderDTO.getId());
        var entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + orderDTO.getId() + "} not found."));

        if (orderDTO.getOrderStatus() == OrderStatus.EM_PROCESSAMENTO) {
            entity.setStatus(OrderStatus.CANCELADO);
            for (Product product : entity.getCart().getProducts()) {
                product.setQuantity(product.getQuantity() + 1);
                productRepository.save(product);
            }
        } else {
            entity.setStatus(OrderStatus.FINALIZADO);
            for (Product product : entity.getCart().getProducts()) {
                if(product.getQuantity() <= 0) product.setActive(false);
                product.setSelledQuantity(product.getSelledQuantity() + 1);
                productRepository.save(product);
            }
        }
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
