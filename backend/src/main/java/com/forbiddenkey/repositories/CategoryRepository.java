package com.forbiddenkey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forbiddenkey.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
