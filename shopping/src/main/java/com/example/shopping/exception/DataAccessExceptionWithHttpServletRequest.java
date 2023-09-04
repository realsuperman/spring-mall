package com.example.shopping.exception;

import lombok.Getter;

@Getter
public class DataAccessExceptionWithHttpServletRequest extends RuntimeException {
    String requestType;

    public DataAccessExceptionWithHttpServletRequest(String message, Throwable cause, String requestType) {
        super(message, cause);
        this.requestType=requestType;
    }
}