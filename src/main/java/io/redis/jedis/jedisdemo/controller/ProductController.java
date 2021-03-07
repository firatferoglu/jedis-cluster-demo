package io.redis.jedis.jedisdemo.controller;

import io.redis.jedis.jedisdemo.model.Product;
import io.redis.jedis.jedisdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping(path = "/products")
    public void save(@RequestBody Product product) {
        service.save(product);
    }

    @GetMapping(path = "/products/{id}")
    public ResponseEntity<Product> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }
}
