package io.redis.jedis.jedisdemo.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;

@Configuration
public class RedisConfiguration {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.jedis.pool.max.total}")
    private int poolMaxTotal;

    @Value("${redis.jedis.pool.max.idle}")
    private int poolMaxIdle;

    @Value("${redis.jedis.pool.min.idle}")
    private int poolMinIdle;

    @Bean
    public JedisClientConfiguration getJedisClientConfiguration() {
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisClientPoolingConfigurationBuilder = JedisClientConfiguration.builder().usePooling();
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(poolMaxTotal);
        genericObjectPoolConfig.setMaxIdle(poolMaxIdle);
        genericObjectPoolConfig.setMinIdle(poolMinIdle);
        return jedisClientPoolingConfigurationBuilder.poolConfig(genericObjectPoolConfig).build();
    }

    @Bean
    public JedisConnectionFactory getJedisConnectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.addClusterNode(new RedisNode(host, port));
        if (!ObjectUtils.isEmpty(password)) {
            redisClusterConfiguration.setPassword(password);
        }
        return new JedisConnectionFactory(redisClusterConfiguration, getJedisClientConfiguration());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getJedisConnectionFactory());
        return redisTemplate;
    }


}
