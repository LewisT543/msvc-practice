package com.lt.customer.controller;

import com.lt.customer.model.Customer;
import com.lt.customer.model.dto.CustomerDTO;
import com.lt.customer.request.CustomerRegistrationRequest;
import com.lt.customer.service.impl.CustomerServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration: {}", customerRegistrationRequest);
        customerServiceImpl.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerServiceImpl.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById
}
