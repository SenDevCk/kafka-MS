package com.chandan.product_service.ctrl;

import com.chandan.base_model.dto.Product;
import com.chandan.base_model.dto.ProductEvent;
import com.chandan.product_service.kafka.ProductProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductCtrl {

    private final ProductProducer productProducer;

    public ProductCtrl(ProductProducer productProducer) {
        this.productProducer = productProducer;
    }


    @PostMapping("create-product")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        String productId= UUID.randomUUID().toString();
        product.setProductId(productId);
         ProductEvent productEvent=ProductEvent.builder()
                 .product(product).message("order status is in pending state")
                 .status("PENDING").build();
        productProducer.sendMessage(productEvent);
        return ResponseEntity.ok("product-created-successfully");
    }
}
