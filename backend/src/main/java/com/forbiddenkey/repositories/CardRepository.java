package com.forbiddenkey.repositories;

import com.forbiddenkey.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

    @Query(value = "SELECT * FROM TB_CARD WHERE CUSTOMER_ID = :id ORDER BY CREATED_AT", nativeQuery = true)
    Optional<List<Card>> findByCard(Long id);

    @Query(value = "SELECT * FROM TB_CARD WHERE NUMBER = :number", nativeQuery = true)
    Card findByNumber(String number);
}
