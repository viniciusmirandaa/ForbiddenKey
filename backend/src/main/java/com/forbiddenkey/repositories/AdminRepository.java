package com.forbiddenkey.repositories;

import com.forbiddenkey.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query(value = "SELECT * FROM TB_ADMIN WHERE USER_ID = :id", nativeQuery = true)
    Admin findByUser(Long id);
}
