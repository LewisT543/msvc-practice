package com.lt.customer.service.interf;

import com.lt.customer.model.Customer;
import com.lt.customer.request.CustomerRegistrationRequest;

import java.util.List;

public interface CustomerService {

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest);
    public List<Customer> getAllCustomers();
    public Customer getCustomerById();
}
