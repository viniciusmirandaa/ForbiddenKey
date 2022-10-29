package com.forbiddenkey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.forbiddenkey.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
