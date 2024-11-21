package com.d288.ryan.dao;

import com.d288.ryan.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "cartItem", path="cart-items")
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
