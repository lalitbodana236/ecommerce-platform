package com.platform.user.exceptions;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
    }
    
    public PasswordMismatchException(String message) {
        super(message);
    }
}
