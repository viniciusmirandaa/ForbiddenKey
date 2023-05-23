package com.forbiddenkey.resources;

import com.forbiddenkey.dto.customerGames.CustomerGamesDTO;
import com.forbiddenkey.entities.CustomerGames;
import com.forbiddenkey.services.CustomerGamesService;
import com.forbiddenkey.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customerGames")
public class CustomerGamesResource {

    @Autowired
    private CustomerGamesService customerGamesService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CustomerGamesDTO>> findAll(){
        List<CustomerGamesDTO> list = customerGamesService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<List<CustomerGamesDTO>> findCustomerGames(){
        List<CustomerGamesDTO> list = customerGamesService.findCustomerGames(customerService.currentCustomerLogged().getId());
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerGamesDTO> update(@PathVariable Long id){
        var customerGamesDTO = customerGamesService.update(id);
        return ResponseEntity.ok().body(customerGamesDTO);
    }

}
