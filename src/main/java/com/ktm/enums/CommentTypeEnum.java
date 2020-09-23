package com.ktm.enums;

public enum CommentTypeEnum {
    REPLY_QUESTION("回复问题", 1), //一级评论
    REPLY_COMMENT("回复评论",2);  //二级评论

    private String message;
    private Integer type;

    CommentTypeEnum(String message, Integer type) {
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
