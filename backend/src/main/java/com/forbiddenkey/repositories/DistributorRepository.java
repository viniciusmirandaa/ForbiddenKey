package com.forbiddenkey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forbiddenkey.entities.Distributor;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long>{

}
