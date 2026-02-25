package com.platform.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        return ResponseEntity.status(404).body(new ErrorResponse(exception.getMessage(), HttpStatusCode.valueOf(404).toString(), HttpStatus.NOT_FOUND.name()));
    }
    
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> passwordMismatchExceptionHandler(PasswordMismatchException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(exception.getMessage(), HttpStatusCode.valueOf(404).toString(), HttpStatus.NOT_FOUND.name()));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(exception.getMessage(), HttpStatusCode.valueOf(404).toString(), HttpStatus.NOT_FOUND.name()));
    }
    
}
