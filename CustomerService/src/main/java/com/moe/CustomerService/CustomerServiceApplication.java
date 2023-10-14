package com.moe.CustomerService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = {
        "com.moe.CustomerService",
        "com.moe.amqp",
        "com.moe.config"
})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.moe.clients")
@PropertySources(
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
)
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}