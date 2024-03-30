package com.esewashopping.customer;

import com.esewashopping.shared.Status;

import java.util.List;

public interface CustomerService {

    CustomerDto updateCustomer(CustomerDto customerDto, Integer cid);

    CustomerDto findCustomerById(Integer cid);

    void deleteCustomer(Integer cid);

    List<CustomerDto> findByStatus(Status status);

    String changeStatus(Integer cid);

    List<CustomerDto> getAllCustomer();

    Integer authentication(String email, String password);



    CustomerDto registerCustomer(CustomerDto customerDto);
}
