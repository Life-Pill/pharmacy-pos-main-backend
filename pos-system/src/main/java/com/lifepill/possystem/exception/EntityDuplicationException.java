package com.lifepill.possystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT) // Use HttpStatus.CONFLICT for entity duplication
public class EntityDuplicationException extends RuntimeException {
    public EntityDuplicationException(String message) {
        super(message);
    }
}
