package com.forbiddenkey.repositories;

import com.forbiddenkey.entities.Card;
import com.forbiddenkey.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    @Query(value = "SELECT * FROM TB_CUSTOMER WHERE USER_ID = :id", nativeQuery = true)
    Optional<Customer> findByUser(Long id);
}
