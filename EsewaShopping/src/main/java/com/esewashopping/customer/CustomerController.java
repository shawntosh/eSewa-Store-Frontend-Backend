package com.esewashopping.customer;

import com.esewashopping.shared.ApiResponse;
import com.esewashopping.shared.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.registerCustomer(customerDto), HttpStatus.CREATED);
    }

    @PostMapping("/update/{cid}")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Integer cid) {
        return ResponseEntity.ok(customerService.updateCustomer(customerDto, cid));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CustomerDto>> getByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(customerService.findByStatus(status));
    }

    @DeleteMapping("/delete/{cid}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Integer cid) {
        customerService.deleteCustomer(cid);
        return new ResponseEntity<>(new ApiResponse("Customer deleted successfully", false), HttpStatus.OK);
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<CustomerDto>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("/getById/{cid}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer cid) {
        return ResponseEntity.ok(customerService.findCustomerById(cid));
    }

    @GetMapping("/changeStatus/{cid}")

    public ResponseEntity<String> changeStatus(@PathVariable Integer cid) {
        return ResponseEntity.ok(customerService.changeStatus(cid));
    }


    @PostMapping("/login")
    public ResponseEntity<Integer> login(@RequestBody LoginRequest loginRequest) {
        Integer customerId = customerService.authentication(loginRequest.getEmail(), loginRequest.getPassword());

        if (customerId != null) {
            return ResponseEntity.ok(customerId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
