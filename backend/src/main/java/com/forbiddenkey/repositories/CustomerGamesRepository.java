package com.forbiddenkey.repositories;

import com.forbiddenkey.entities.CustomerGames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerGamesRepository extends JpaRepository<CustomerGames, Long> {

    @Query(value = "SELECT * FROM TB_CUSTOMER_GAMES WHERE CUSTOMER_ID = :customerId", nativeQuery = true)
    List<CustomerGames> findGamesbyCustomer(Long customerId);
}
