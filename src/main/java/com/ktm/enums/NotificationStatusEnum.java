package com.ktm.enums;


import lombok.Data;

public enum NotificationStatusEnum {
    UNREAD("未读", 0),
    ALREADY_READ("已读", 1);


    private String message;
    private Integer status;


    NotificationStatusEnum(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
