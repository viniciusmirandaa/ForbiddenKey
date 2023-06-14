package com.forbiddenkey.repositories;

import com.forbiddenkey.entities.Cart;
import com.forbiddenkey.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCustomerIdAndCurrentCartTrue(Long customer_id);

    @Query(value = "SELECT * FROM TB_CART WHERE CURRENT_CART = TRUE", nativeQuery = true)
    List<Cart> findAllByCartTrue();

    @Query(value = "SELECT * FROM TB_CART WHERE CUSTOMER = :customer AND DISCOUNT_VALUE != 0 AND YEAR(CREATED_AT) = YEAR(CURRENT_DATE)", nativeQuery = true)
    Cart findCartByUsedCoupon(Customer customer);

}
