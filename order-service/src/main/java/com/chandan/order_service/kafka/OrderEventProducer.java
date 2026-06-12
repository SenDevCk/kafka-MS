package com.chandan.order_service.kafka;

import com.chandan.base_model.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;



@Service
public class OrderEventProducer {

    private static final Logger logger= LoggerFactory.getLogger(OrderEventProducer.class);
    private final NewTopic topic;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderEventProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

   public void sendEvent(OrderEvent orderEvent){
       Message<OrderEvent> message= MessageBuilder.
               withPayload(orderEvent).setHeader(KafkaHeaders.TOPIC,topic.name()).build();
       kafkaTemplate.send(message);
   }


}
