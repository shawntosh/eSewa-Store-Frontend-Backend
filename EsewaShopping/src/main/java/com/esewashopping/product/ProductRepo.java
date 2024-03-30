package com.esewashopping.product;

import com.esewashopping.category.Category;
import com.esewashopping.customer.Customer;
import com.esewashopping.shared.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(Category category);

    List<Product> findByNameContains(String name);

    List<Product> findByCustomer(Customer customer);

    Product findByNameAndPriceAndQuantity(String name, Float price, Integer quantity);

    Optional<Object> findByName(String name);

    List<Product> findProductByStatus(Status status);
}
