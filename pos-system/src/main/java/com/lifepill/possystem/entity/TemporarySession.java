package com.lifepill.possystem.entity;

import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash("TemporarySession")
public class TemporarySession {
    @Id
    private String employerEmail;
    private String employerPassword;
    private long expirationTime; // Unix timestamp for expiration time
    //private int pin;
}