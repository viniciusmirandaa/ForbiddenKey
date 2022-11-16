package com.forbiddenkey.repositories;

import com.forbiddenkey.entities.CustomerGames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerGamesRepository extends JpaRepository<CustomerGames, Long> {
}
