package io.redis.jedis.jedisdemo.service;

import io.redis.jedis.jedisdemo.model.Product;
import io.redis.jedis.jedisdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    public void save(Product product) {
        repository.save(product);
    }

    public Product get(String id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return repository.findById(id).orElse(null);
    }
}
