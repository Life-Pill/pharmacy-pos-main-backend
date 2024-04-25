package com.lifepill.possystem.advisor;

import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.util.StandardResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "Error Cumming", e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Log the exception for debugging purposes
        ex.printStackTrace();

        // Provide a meaningful error message to the client
        String errorMessage = "An error occurred while processing your request. Please try again later.";

        // You can customize the error message based on the specific constraint violation if needed

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
