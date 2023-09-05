package com.moe.controller;


import com.moe.service.FraudCheckService;
import com.moe.service.FraudCheckresponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;



    @GetMapping(path = "{customerId}")
    public FraudCheckresponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isFraudulentCustomer = fraudCheckService.isFradudulentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);
        return new FraudCheckresponse(isFraudulentCustomer);

    }
}
