package com.forbiddenkey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forbiddenkey.entities.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long>{

}
