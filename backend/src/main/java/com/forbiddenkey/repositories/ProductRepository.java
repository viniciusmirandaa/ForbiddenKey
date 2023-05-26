package com.forbiddenkey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.forbiddenkey.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query(value = "SELECT * FROM TB_PRODUCT WHERE ACTIVE = TRUE AND QUANTITY != 0", nativeQuery = true)
    List<Product> findByActiveTrue();

    @Query(value = "SELECT TOP 5 * FROM TB_PRODUCT WHERE ACTIVE = TRUE AND SELLED_QUANTITY != 0 ORDER BY SELLED_QUANTITY DESC", nativeQuery = true)
    List<Product> findMostSelled();
}
