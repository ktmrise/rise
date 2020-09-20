package com.ktm.Exception;

public enum CustomizeErrorCode {
    PAGE_NOT_FOUND("404"),
    QUESTION_NOT_FOUND("你找的问题不在了,换个问题试试");

    private final String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
