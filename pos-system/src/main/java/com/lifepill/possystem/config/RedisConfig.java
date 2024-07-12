package com.lifepill.possystem.config;


import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /**
     * Configures the Jedis connection factory for Redis.
     *
     * @return JedisConnectionFactory configured with Redis standalone configuration.
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("redis");
        redisStandaloneConfiguration.setPort(6379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    /**
     * Configures the Redis template for key-value serialization.
     *
     * @return RedisTemplate configured with String key serializer and Jackson JSON value serializer.
     */
    @Bean
    public RedisTemplate<String, EmployerAuthDetailsResponseDTO> redisTemplate() {
        RedisTemplate<String, EmployerAuthDetailsResponseDTO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(EmployerAuthDetailsResponseDTO.class));
        return redisTemplate;
    }
}
