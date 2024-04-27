package com.lifepill.possystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Internal server error exception.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {
    /**
     * Instantiates a new Internal server error exception.
     *
     * @param message the message
     */
    public InternalServerErrorException(String message) {
        super(message);
    }
}