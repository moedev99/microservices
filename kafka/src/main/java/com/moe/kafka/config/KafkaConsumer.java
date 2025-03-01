package com.moe.kafka.config;

import com.moe.clients.notification.NotificationRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumer {


    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfig(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return props;
    }

    @Bean
    public ConsumerFactory<String, NotificationRequest> consumerFactory(
            Map<String, Object> consumerConfig
    ){
        JsonDeserializer<NotificationRequest> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("com.moe.notificationService");
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig,
                new StringDeserializer(),
                jsonDeserializer
        );
    }


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, NotificationRequest>> factory(
            ConsumerFactory<String, NotificationRequest> consumerFactory
    ){
        ConcurrentKafkaListenerContainerFactory<String, NotificationRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }


}
