package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cacheAuthenticationData(String key, Object data) {
        try {
            redisTemplate.opsForValue().set(key, data);
        } catch (Exception e) {
            // Log the error for debugging purposes
            e.printStackTrace();
            // Throw a custom exception or handle the error accordingly
            throw new RuntimeException("Error caching authentication data in Redis: " + e.getMessage());
        }
    }

    public EmployerAuthDetailsResponseDTO getAuthenticationData(String username) {
        return (EmployerAuthDetailsResponseDTO) redisTemplate.opsForValue().get(username);
    }

    public void removeAuthenticationData(String username) {
        redisTemplate.delete(username);
    }
}


