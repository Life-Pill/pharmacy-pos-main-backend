package com.lifepill.possystem.dto;

import lombok.Data;

@Data
public class VerifyPinRequestDTO {
    private String username;
    private int pin;
}
