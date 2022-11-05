package com.forbiddenkey.repositories;

import com.forbiddenkey.entities.Cart;
import com.forbiddenkey.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM tb_item WHERE PRODUCT = :product_id AND CART = :cart_id", nativeQuery = true)
    Item findByCartAndProduct(Long cart_id, Long product_id);

    List<Item> findAllByCart(Cart cart);
}
