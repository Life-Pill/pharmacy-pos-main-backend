package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private static final String EMPLOYER_DETAILS_PREFIX = "employer_details::";

    @Autowired
    private RedisTemplate<String, EmployerAuthDetailsResponseDTO> redisTemplate;

    public void cacheEmployerDetails(EmployerAuthDetailsResponseDTO employerDetails) {
        String key = EMPLOYER_DETAILS_PREFIX + employerDetails.getEmployerEmail();
        redisTemplate.opsForValue().set(key, employerDetails, 24, TimeUnit.HOURS); // Cache for 24 hours
    }

    public EmployerAuthDetailsResponseDTO getEmployerDetails(String username) {
        String key = EMPLOYER_DETAILS_PREFIX + username;
        return redisTemplate.opsForValue().get(key);
    }

    public void removeEmployerDetails(String username) {
        String key = EMPLOYER_DETAILS_PREFIX + username;
        redisTemplate.delete(key);
    }
}
