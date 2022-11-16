package com.forbiddenkey.resources;

import com.forbiddenkey.dto.customerGames.CustomerGamesDTO;
import com.forbiddenkey.services.CustomerGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customerGames")
public class CustomerGamesResource {

    @Autowired
    private CustomerGamesService customerGamesService;

    @GetMapping
    public ResponseEntity<List<CustomerGamesDTO>> findAll(){
        List<CustomerGamesDTO> list = customerGamesService.findAll();
        return ResponseEntity.ok().body(list);
    }

}
