package com.lifepill.possystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Entity duplication exception.
 */
@ResponseStatus(value = HttpStatus.CONFLICT) // Use HttpStatus.CONFLICT for entity duplication
public class EntityDuplicationException extends RuntimeException {
    /**
     * Instantiates a new Entity duplication exception.
     *
     * @param message the message
     */
    public EntityDuplicationException(String message) {
        super(message);
    }
}
