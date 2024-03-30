package com.esewashopping.cart.repo;

import com.esewashopping.cart.entity.CartItem;
import com.esewashopping.customer.Customer;
import com.esewashopping.product.Product;
import com.esewashopping.product.ProductResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

    @Query("SELECT new com.esewashopping.product.ProductResponseDTO(c.product.pid, c.product.name, c.product.price, c.quantity, c.product.description, c.product.productImage) " +
            "FROM CartItem c WHERE c.customer.cId = :customerId")
    List<ProductResponseDTO> findProductsByCustomerId(@Param("customerId") Integer customerId);
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.customer.cId = :customerId AND c.product.pid = :productId")
    void removeProductFromCart(@Param("customerId") Integer customerId, @Param("productId") Integer productId);




}


