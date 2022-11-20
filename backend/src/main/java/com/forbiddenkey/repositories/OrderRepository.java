package com.forbiddenkey.repositories;

import com.forbiddenkey.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT TOP 1 id FROM TB_ORDER ORDER BY ID DESC", nativeQuery = true)
    Long findLastInserted();

    @Query(value = "SELECT * FROM TB_ORDER WHERE CUSTOMER_ID = :id", nativeQuery = true)
    List<Order> findByCustomerId(Long id);
}
