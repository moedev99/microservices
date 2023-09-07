package com.moe.CustomerService.service;

import com.moe.CustomerService.controller.customerRequest.CustomerRegistrationRequest;
import com.moe.CustomerService.entity.Customer;
import com.moe.CustomerService.repository.CustomerRegistrationRepository;
import com.moe.clients.fraud.FraudClient;
import com.moe.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerRegistrationService {
    private final CustomerRegistrationRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    public void registerCustomer(CustomerRegistrationRequest request){

        Customer customer = Customer.builder()
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName()).build();
//        todo: check if the email is valid
//        todo: check if email not token

            customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckresponse = fraudClient.isFraudster(customer.getId());
        if (fraudCheckresponse.isFraudster()){
            throw new IllegalStateException("Fraudster");
        }

//        todo: send notification


    }
}
