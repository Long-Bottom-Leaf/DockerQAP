package com.project.dockerapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(
                new ErrorResponse(message, status.value(), LocalDateTime.now()),
                status
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneral(Exception exception) {
        return buildResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
