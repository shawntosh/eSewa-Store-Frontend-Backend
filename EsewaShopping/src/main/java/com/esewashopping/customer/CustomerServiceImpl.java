package com.esewashopping.customer;

import com.esewashopping.exception.ResourceNotFoundException;
import com.esewashopping.shared.Role;
import com.esewashopping.shared.Status;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper modelMapper;

    private final CustomerRepo customerRepo;



    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Integer cid) {

        Customer existingCustomer = customerRepo.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", cid));
        Customer updateCustomer = modelMapper.map(customerDto, Customer.class);
        existingCustomer.setContact(updateCustomer.getContact());
        existingCustomer.setEmail(updateCustomer.getEmail());
        existingCustomer.setAddress(updateCustomer.getAddress());
        existingCustomer.setFullName(updateCustomer.getFullName());
        existingCustomer.setPassword(updateCustomer.getPassword());
        return modelMapper.map(customerRepo.save(existingCustomer), CustomerDto.class);

    }

    @Override
    public CustomerDto findCustomerById(Integer cid) {
        Customer customer = customerRepo.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", cid));
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public void deleteCustomer(Integer cid) {
        Customer customer = customerRepo.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", cid));

        customer.setStatus(Status.DELETED);
        customerRepo.save(customer);
    }

    @Override
    public List<CustomerDto> findByStatus(Status status) {
        List<Customer> statusCustomer = customerRepo.findByStatus(status);
        return statusCustomer.stream().map(li -> modelMapper.map(li, CustomerDto.class)).toList();
    }

    @Override
    public String changeStatus(Integer cid) {
        Customer customer = customerRepo.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", cid));
        customer.setStatus(Status.VERIFIED);
        customerRepo.save(customer);
        return "Successfully change Status";
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> listOfCustomer = customerRepo.findAll();
        return listOfCustomer.stream().map(list -> modelMapper.map(list, CustomerDto.class)).toList();
    }

    @Override
    public Integer authentication(String email, String password) {
        Customer customer = customerRepo.findByEmailAndPassword(email, password);
        if (customer != null) {
            return customer.getCId();
        } else return null;
    }

    @Override
    public CustomerDto registerCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer.setStatus(Status.UNVERIFIED);
        customer.setRole(Role.USER);
        Customer saveCustomer = customerRepo.save(customer);
        return modelMapper.map(saveCustomer, CustomerDto.class);
    }


}
