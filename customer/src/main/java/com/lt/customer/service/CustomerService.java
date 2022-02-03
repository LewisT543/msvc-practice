package com.lt.customer.service;

import com.lt.customer.model.Customer;
import com.lt.customer.repository.CustomerRepository;
import com.lt.customer.request.CustomerRegistrationRequest;
import com.lt.customer.response.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
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
}
