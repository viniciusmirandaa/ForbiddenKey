package com.forbiddenkey.resources;

//import com.forbiddenkey.services.CardService;
import com.forbiddenkey.dto.card.CardDTO;
import com.forbiddenkey.services.CardService;
import com.forbiddenkey.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cards")
public class CardResource {

    @Autowired
    private CardService cardService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CardDTO>> findAll(){
        List<CardDTO> list = cardService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<CardDTO> insert(@RequestBody CardDTO cardDTO){
        cardDTO = cardService.insert(cardDTO, customerService.currentCustomerLogged());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cardDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
