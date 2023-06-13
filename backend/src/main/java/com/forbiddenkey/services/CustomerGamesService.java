package com.forbiddenkey.services;

import com.forbiddenkey.dto.customerGames.CustomerGamesDTO;
import com.forbiddenkey.dto.order.OrderDTO;
import com.forbiddenkey.entities.CustomerGames;
import com.forbiddenkey.entities.Product;
import com.forbiddenkey.repositories.CustomerGamesRepository;
import com.forbiddenkey.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CustomerGamesService {

    @Autowired
    private CustomerGamesRepository customerGamesRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<CustomerGamesDTO> findAll() {
        List<CustomerGames> list = customerGamesRepository.findAll();
        return list.stream().map(CustomerGamesDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerGamesDTO> findCustomerGames(Long id) {
        List<CustomerGames> list = customerGamesRepository.findGamesbyCustomer(id);
        return list.stream().map(CustomerGamesDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void insert(OrderDTO orderDTO) {
        var order = orderRepository.findById(orderDTO.getId()).get();

        for (Product product : order.getCart().getProducts()) {
            var customerGames = new CustomerGames(product, order.getCustomer(), order, createActivationKey(), false);
            customerGamesRepository.save(customerGames);
        }
    }

    @Transactional
    public CustomerGamesDTO update(Long id) {
        var customerGames = customerGamesRepository.findById(id).get();
        customerGames.setSeen(true);
        customerGamesRepository.save(customerGames);
        return new CustomerGamesDTO(customerGames);
    }

    private String createActivationKey() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();
        while (key.length() <= 12) { // activation key length
            int index = (int) (rnd.nextFloat() * chars.length());
            key.append(chars.charAt(index));
        }
        return key.toString();
    }
}
