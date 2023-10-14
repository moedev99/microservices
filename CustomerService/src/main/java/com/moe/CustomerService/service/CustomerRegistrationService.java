package com.moe.CustomerService.service;

import com.moe.CustomerService.controller.customerRequest.CustomerRegistrationRequest;
import com.moe.CustomerService.entity.Customer;
import com.moe.CustomerService.repository.CustomerRegistrationRepository;
import com.moe.amqp.producer.RabbitMQMessageProducer;
import com.moe.clients.fraud.FraudClient;
import com.moe.clients.fraud.FraudCheckResponse;
import com.moe.clients.notification.NotificationRequest;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class CustomerRegistrationService {

    @Autowired
    private  CustomerRegistrationRepository customerRepository;
    @Autowired
    private  FraudClient fraudClient;
    @Autowired
    private RabbitMQMessageProducer rabbitMQMessageProducer;

//    @Autowired
//    private KafkaTemplate kafkaTemplate;

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


        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), customer.getEmail(), "You have signed in successfully");
        rabbitMQMessageProducer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");
//        kafkaTemplate.send("notification", notificationRequest);


    }
}
