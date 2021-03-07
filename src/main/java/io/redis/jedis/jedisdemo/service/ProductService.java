package io.redis.jedis.jedisdemo.service;

import io.redis.jedis.jedisdemo.model.Product;

public interface ProductService {
    Product get(String id);
    void save(Product product);
}
