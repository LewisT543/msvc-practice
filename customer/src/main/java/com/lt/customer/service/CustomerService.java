package com.lt.customer.service;

import com.lt.customer.model.Customer;
import com.lt.customer.repository.CustomerRepository;
import com.lt.customer.request.CustomerRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.save(customer);
    }
}
