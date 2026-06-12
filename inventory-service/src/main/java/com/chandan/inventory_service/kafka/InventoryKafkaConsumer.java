package com.chandan.inventory_service.kafka;

import com.chandan.base_model.dto.OrderEvent;
import com.chandan.base_model.dto.ProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryKafkaConsumer {
    Logger log = LoggerFactory.getLogger(InventoryKafkaConsumer.class);

    @KafkaListener(topics = "${spring,kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ProductEvent productEvent) {
        log.info(String.format("The event accepted from product service is %s", productEvent.toString()));
        //now datbase code will happen here

    }

    @KafkaListener(
            topics = "${spring.kafka.topic.order}",
            groupId = "${spring.kafka.consumer.order-group-id}"
    )
    public void consumeOrder(OrderEvent orderEvent) {
        log.info("Received Order Event: {}", orderEvent);
        //database code will be done here
    }
}
