package com.fantasybaby.dee.code.serizlize.redistemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dee.code.serizlize.redistemplate"
        , exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class RedisTemplateMistakesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisTemplateMistakesApplication.class, args);
    }

     @Bean
     public <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
         RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
         redisTemplate.setConnectionFactory(redisConnectionFactory);
         Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
         //把类型信息作为属性写入Value
         objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
         jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
         redisTemplate.setKeySerializer(RedisSerializer.string());
         redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
         redisTemplate.setHashKeySerializer(RedisSerializer.string());
         redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
         redisTemplate.afterPropertiesSet();
         return redisTemplate;
     }
  /*  @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }*/

}

