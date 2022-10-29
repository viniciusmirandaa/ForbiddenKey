package com.forbiddenkey.repositories;

import com.forbiddenkey.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    @Query(value = "SELECT * FROM TB_CUSTOMER WHERE USER_ID = :id", nativeQuery = true)
    Customer findByUser(Long id);
}
