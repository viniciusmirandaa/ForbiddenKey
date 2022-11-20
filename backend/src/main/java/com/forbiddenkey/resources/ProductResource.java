package com.forbiddenkey.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.forbiddenkey.services.CustomerGamesService;
import com.forbiddenkey.services.CustomerService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.forbiddenkey.dto.product.ProductDTO;
import com.forbiddenkey.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerGamesService customerGamesService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/avaible")
    public ResponseEntity<List<ProductDTO>> findAllAvaible() {
        List<ProductDTO> list = productService.findAllAvaible();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/mostSelled")
    public ResponseEntity<List<ProductDTO>> findMostSelled() {
        List<ProductDTO> list = productService.findMostSelled();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = productService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        dto = productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @PathVariable Long id, @RequestBody ProductDTO dto) {
        dto = productService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> inactivate(@PathVariable Long id) {
        productService.inactivate(id);
        return ResponseEntity.noContent().build();
    }
}
