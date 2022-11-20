package com.forbiddenkey.services;

import java.util.*;
import java.util.stream.Collectors;

import com.forbiddenkey.dto.category.CategoryDTO;
import com.forbiddenkey.dto.customerGames.CustomerGamesDTO;
import com.forbiddenkey.entities.Category;
import com.forbiddenkey.entities.CustomerGames;
import com.forbiddenkey.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forbiddenkey.dto.product.ProductDTO;
import com.forbiddenkey.entities.Product;
import com.forbiddenkey.services.exceptions.DatabaseException;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private DistributorRepository distributorRepository;

    @Autowired
    private CustomerGamesRepository customerGamesRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAllAvaible() {
        List<Product> list = productRepository.findByActiveTrue();
        return list.stream().map(product -> new ProductDTO(product, product.getCategories())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> list = productRepository.findAll();
        return list.stream().map(product -> new ProductDTO(product, product.getCategories())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findMostSelled() {
        List<Product> list = productRepository.findMostSelled();
        return list.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity.setActive(true);
        entity = productRepository.save(entity);
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Optional<Product> entity = productRepository.findById(id);
        Product product = entity.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        copyDtoToEntity(dto, product);
        product = productRepository.save(product);
        return new ProductDTO(product, product.getCategories());
    }

    public void inactivate(Long id) {
        try {
            var obj = productRepository.findById(id);
            var entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));
            entity.setActive(false);
            productRepository.save(entity);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setQuantity(dto.getQuantity());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());
        entity.setDeveloper(developerRepository.getReferenceById(dto.getDeveloperDTO().getId()));
        entity.setDistributor(distributorRepository.getReferenceById(dto.getDistributorDTO().getId()));
        entity.setLaunchDate(dto.getLaunchDate());
        entity.getCategories().clear();

        for (CategoryDTO catDTO : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(catDTO.getId());
            entity.getCategories().add(category);
        }
    }
}
