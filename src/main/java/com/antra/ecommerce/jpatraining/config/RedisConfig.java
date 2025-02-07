package com.antra.ecommerce.jpatraining.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConditionalOnBean(RedisConnectionFactory.class)
public class RedisConfig {
     @Bean
     public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
         RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
         redisTemplate.setKeySerializer(new StringRedisSerializer());
         redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
         redisTemplate.setConnectionFactory(redisConnectionFactory);
         return redisTemplate;
     }
}
