package com.forbiddenkey.resources;

import com.forbiddenkey.dto.order.OrderDTO;
import com.forbiddenkey.entities.Enum.OrderStatus;
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

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> list = orderService.findAll(customerService.currentCustomerLogged());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> insert(@PathVariable Long id) {
        var orderDto = orderService.insert(id);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDto.getId()).toUri();
        return ResponseEntity.created(uri).body(orderDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id){
        var orderDTO = orderService.update(id);
        return ResponseEntity.ok().body(orderDTO);
    }
}
