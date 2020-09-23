package com.ktm.enums;

public enum  NotificationTypeEnum {
    REPLY_QUESTION("回复了问题",1),
    REPLY_COMMMENT("回复了评论", 0);


    private String message;
    private Integer type;


    NotificationTypeEnum(String message, Integer type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
