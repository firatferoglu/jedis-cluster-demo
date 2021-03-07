package io.redis.jedis.jedisdemo.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Product")
public class Product implements Serializable {
    private String id;
    private String name;
    private Double price;
}
