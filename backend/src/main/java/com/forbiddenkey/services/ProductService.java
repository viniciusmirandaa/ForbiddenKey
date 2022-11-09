package com.forbiddenkey.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.forbiddenkey.dto.CategoryDTO;
import com.forbiddenkey.entities.Category;
import com.forbiddenkey.repositories.DeveloperRepository;
import com.forbiddenkey.repositories.DistributorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forbiddenkey.dto.ProductDTO;
import com.forbiddenkey.entities.Product;
import com.forbiddenkey.repositories.CategoryRepository;
import com.forbiddenkey.repositories.ProductRepository;
import com.forbiddenkey.services.exceptions.DatabaseException;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private DistributorRepository distributorRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> list = repository.findAll(pageable);
        return list.map(x -> new ProductDTO(x, x.getCategories()));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Optional<Product> entity = repository.findById(id);
        Product product = entity.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        copyDtoToEntity(dto, product);
        product = repository.save(product);
        return new ProductDTO(product, product.getCategories());
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setQuantity(dto.getQuantity());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());
        entity.setDeveloper(developerRepository.getReferenceById(dto.getDeveloper().getId()));
        entity.setDistributor(distributorRepository.getReferenceById(dto.getDistributor().getId()));
        entity.setLaunchDate(dto.getLaunchDate());
        entity.getCategories().clear();

        for (CategoryDTO catDTO : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(catDTO.getId());
            entity.getCategories().add(category);
        }
    }
}
