package com.esewashopping.shared;


import com.esewashopping.customer.Customer;
import com.esewashopping.customer.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Utility implements CommandLineRunner {

    private final CustomerRepo customerRepo;

    @Override
    public void run(String... args) throws Exception {
        List<Customer> customers = customerRepo.findByRole(Role.ADMIN);
        if (customers.isEmpty()) {
            Customer customer = new Customer();
            customer.setFullName("Rabindra Adhikari");
            customer.setEmail("adkraj242@gmail.com");
            customer.setPassword("123123");
            customer.setAddress("Amamnagar");
            customer.setContact(981492287);
            customer.setStatus(Status.VERIFIED);
            customer.setRole(Role.ADMIN);
            customerRepo.save(customer);
        }
    }
}
