package com.chandan.order_service.ctrl;

import com.chandan.base_model.dto.Order;
import com.chandan.base_model.dto.OrderEvent;
import com.chandan.order_service.kafka.OrderEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderCtrl {

    OrderEventProducer orderEventProducer;

    public OrderCtrl(OrderEventProducer orderEventProducer) {
        this.orderEventProducer = orderEventProducer;
    }

    @PostMapping("/crete-order")
    public ResponseEntity<String> saveOrder(@RequestBody Order order){
        String orderid=   UUID.randomUUID().toString();
        order.setOrderId(orderid);
        OrderEvent orderevent=OrderEvent.builder().message("Currently Order Pending !").status("Pending").order(order).build();
        orderEventProducer.sendEvent(orderevent);
        return ResponseEntity.ok("Order-Created");
    }
}
