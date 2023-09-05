package com.moe.CustomerService.service;

import com.moe.CustomerService.controller.customerRequest.CustomerRegistrationRequest;
import com.moe.CustomerService.entity.Customer;
import com.moe.CustomerService.repository.CustomerRegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerRegistrationService {
    private final CustomerRegistrationRepository customerRepository;
    private final RestTemplate restTemplate;
    public void registerCustomer(CustomerRegistrationRequest request){

        Customer customer = Customer.builder()
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName()).build();
//        todo: check if the email is valid
//        todo: check if email not token

        customerRepository.saveAndFlush(customer);
//        todo: check if fraudster
        FraudCheckResponse fraudCheckresponse =  restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
                );
        if (fraudCheckresponse.isFraudster()){
            throw new IllegalStateException("Fraudster");
        }

//        todo: send notification


    }
}
