package com.moe.CustomerService.service;

import com.moe.CustomerService.controller.customerRequest.CustomerRegistrationRequest;
import com.moe.CustomerService.entity.Customer;
import com.moe.CustomerService.repository.CustomerRegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerRegistrationService(CustomerRegistrationRepository customerRepository) {


    public void registerCustomer(CustomerRegistrationRequest request){

        Customer customer = Customer.builder()
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName()).build();
//        todo: check if the email is valid
//        todo: check if email not token
//        todo: store customer in db
        customerRepository.save(customer);


    }
}
