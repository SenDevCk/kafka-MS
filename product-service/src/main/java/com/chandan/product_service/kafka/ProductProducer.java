package com.chandan.product_service.kafka;

import com.chandan.base_model.dto.ProductEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;



@Service
public class ProductProducer {
   private static final Logger log= LoggerFactory.getLogger(ProductProducer.class);

   private final NewTopic topic;

   private final KafkaTemplate<String, ProductEvent> kafkaTemplate;

   public ProductProducer(NewTopic topic, KafkaTemplate<String, ProductEvent> kafkaTemplate) {
      this.topic = topic;
      this.kafkaTemplate = kafkaTemplate;
   }

   public void sendMessage(ProductEvent productEvent){
      log.info(productEvent.toString());
      Message<ProductEvent> message= MessageBuilder.withPayload(productEvent)
              .setHeader(KafkaHeaders.TOPIC,topic.name())
              .build();
      kafkaTemplate.send(message);
   }
}
