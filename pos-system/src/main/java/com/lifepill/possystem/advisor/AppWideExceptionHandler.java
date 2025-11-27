package com.lifepill.possystem.advisor;

import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.util.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for handling application-wide exceptions.
 */
@RestControllerAdvice
public class AppWideExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AppWideExceptionHandler.class);

    /**
     * Handles NotFoundException by returning a ResponseEntity with a corresponding error message.
     * @param e The NotFoundException that was thrown
     * @return ResponseEntity with a StandardResponse containing the error details
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {
        logger.warn("Resource not found: {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new StandardResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "Error",
                        e.getMessage()
                ));
    }

    /**
     * Handles DataIntegrityViolationException by returning a ResponseEntity with a corresponding error message.
     * @param ex The DataIntegrityViolationException that was thrown
     * @return ResponseEntity with a StandardResponse containing the error details
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        logger.error("Data integrity violation: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new StandardResponse(
                        HttpStatus.CONFLICT.value(),
                        "DATA_INTEGRITY_ERROR",
                        "A conflict occurred while processing your request."
                ));
    }

    /**
     * Handles general exceptions by returning a ResponseEntity with a generic error message.
     * @param ex The exception that was thrown
     * @return ResponseEntity with a StandardResponse containing the error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleGeneralException(Exception ex) {
        logger.error("Unhandled exception", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StandardResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "INTERNAL_SERVER_ERROR",
                        "An unexpected error occurred. Please try again later."
                ));
    }
}
