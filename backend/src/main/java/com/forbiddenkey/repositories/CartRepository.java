package com.forbiddenkey.repositories;

import com.forbiddenkey.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository <Cart, Long>{

    Optional<Cart> findByCustomerIdAndCurrentCartTrue(Long customer_id);
    @Query(value = "SELECT * FROM TB_CART WHERE CURRENT_CART = TRUE", nativeQuery = true)
    List<Cart> findAllByCartTrue();

}
