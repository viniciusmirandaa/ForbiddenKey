package com.forbiddenkey.repositories;

import com.forbiddenkey.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository <Cart, Long>{

    Optional<Cart> findByCustomerIdAndCurrentCartTrue(Long customer_id);

    Cart findByIdAndCurrentCartTrue(Long cart_id);
}
