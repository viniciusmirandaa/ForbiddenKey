package com.forbiddenkey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forbiddenkey.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
