package com.lt.customer.service.impl;

import com.lt.customer.model.Customer;
import com.lt.customer.model.dto.CustomerDTO;
import com.lt.customer.repository.CustomerRepository;
import com.lt.customer.request.CustomerRegistrationRequest;
import com.lt.customer.response.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);

        // Check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if (fraudCheckResponse.isFraudster()) {
            System.out.println("fraudster");
        }
        // todo: send notification
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(
                customer -> new CustomerDTO(customer.getId(), customer.getFirstName(),
                                            customer.getLastName(), customer.getEmail()))
                .collect(Collectors.toList());
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }


}
