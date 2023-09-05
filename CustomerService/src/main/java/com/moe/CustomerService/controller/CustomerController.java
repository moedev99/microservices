package com.moe.CustomerService.controller;


import com.moe.CustomerService.controller.customerRequest.CustomerRegistrationRequest;
import com.moe.CustomerService.service.CustomerRegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController{

    private final CustomerRegistrationService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequestRequest){
        log.info("new customer registration {}", customerRegistrationRequestRequest);

        customerService.registerCustomer(customerRegistrationRequestRequest);

    }
}
