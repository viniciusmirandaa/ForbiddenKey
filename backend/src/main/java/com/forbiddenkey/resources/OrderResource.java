package com.forbiddenkey.resources;

import com.forbiddenkey.dto.order.OrderDTO;
import com.forbiddenkey.entities.Enum.OrderStatus;
import com.forbiddenkey.services.CartService;
import com.forbiddenkey.services.CustomerGamesService;
import com.forbiddenkey.services.CustomerService;
import com.forbiddenkey.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerGamesService customerGamesService;

    @Autowired
    private CartService cartService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> finById(@PathVariable Long id) {
        var order= orderService.findById(id);
        return ResponseEntity.ok().body(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> list = orderService.findAll(customerService.currentCustomerLogged());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> insert() {
        var orderDto = orderService.insert(cartService.findCurrentCart(customerService.currentCustomerLogged()));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDto.getId()).toUri();
        return ResponseEntity.created(uri).body(orderDto);
    }

    @PutMapping
    public ResponseEntity<OrderDTO> updateStatus(@RequestBody OrderDTO orderDTO){
        orderDTO = orderService.update(orderDTO);
        if(orderDTO.getOrderStatus() == OrderStatus.FINALIZADO) customerGamesService.insert(orderDTO);
        return ResponseEntity.ok().body(orderDTO);
    }
}
