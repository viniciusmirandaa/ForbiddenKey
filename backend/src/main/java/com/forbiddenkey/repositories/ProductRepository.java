package com.forbiddenkey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.forbiddenkey.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findByActiveTrue();

    @Query(value = "SELECT * FROM TB_PRODUCT", nativeQuery = true)
    List<Product> findMostSelled(List<Long> ids);
}
