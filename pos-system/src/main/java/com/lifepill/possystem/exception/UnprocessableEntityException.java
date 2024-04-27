package com.lifepill.possystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Unprocessable entity exception.
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {
    /**
     * Instantiates a new Unprocessable entity exception.
     *
     * @param message the message
     */
    public UnprocessableEntityException(String message) {
        super(message);
    }
}