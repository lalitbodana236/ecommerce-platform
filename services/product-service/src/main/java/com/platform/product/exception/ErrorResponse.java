package com.platform.product.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    String errorMessage;
    
    String status;
    
    String errorCode;
    
    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public ErrorResponse(String errorMessage, String status, String errorCode) {
        this.errorMessage = errorMessage;
        this.status = status;
        this.errorCode = errorCode;
    }
}
