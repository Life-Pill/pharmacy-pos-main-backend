package com.lifepill.possystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Invalid request exception.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {
    /**
     * Instantiates a new Invalid request exception.
     *
     * @param message the message
     */
    public InvalidRequestException(String message) {
        super(message);
    }
}