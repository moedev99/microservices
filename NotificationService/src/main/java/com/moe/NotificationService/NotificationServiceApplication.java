package com.moe.NotificationService;


import com.moe.NotificationService.config.NotificationConfig;
import com.moe.amqp.producer.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.moe.NotificationService",
                "com.moe.amqp"
        }
)
@EnableEurekaClient
@PropertySources(
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
)
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }


}
