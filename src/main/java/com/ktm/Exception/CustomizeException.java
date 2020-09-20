package com.ktm.Exception;

public class CustomizeException extends RuntimeException {

    private final String message;

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
