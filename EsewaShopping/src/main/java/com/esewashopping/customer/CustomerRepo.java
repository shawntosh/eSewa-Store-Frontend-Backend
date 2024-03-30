package com.esewashopping.customer;

import com.esewashopping.shared.Role;
import com.esewashopping.shared.Status;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    List<Customer> findByStatus(Status status);


    //just for the login later replace by JWT token

    Customer findByEmailAndPassword(String email, String password);

     List<Customer> findByRole(Role role);



    Optional<Customer> findByEmail(String email);




}
