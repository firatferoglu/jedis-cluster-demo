package io.redis.jedis.jedisdemo.repository;

import io.redis.jedis.jedisdemo.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
}
