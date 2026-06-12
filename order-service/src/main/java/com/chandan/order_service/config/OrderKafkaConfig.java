package com.chandan.order_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class OrderKafkaConfig {

    @Value("${spring.kafka.topic.order}")
    String topicName;

    @Bean
    public NewTopic orderTopic(){
        return TopicBuilder.name(topicName).build();
    }
}
