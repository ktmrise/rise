package com.ktm.Exception;

public enum CustomizeErrorCode {
    PAGE_NOT_FOUND("404"),
    QUESTION_NOT_FOUND("你找的问题不在了,换个问题试试"),
    PARAM_NOT_FOUND("未传递父ID"),
    COMMENT_QUESTION_NOT_FOUND("你评论的问题已经不在了"),
    COMMENT_NOT_FOUND("你回复的评论已经不在了"),
    FILE_UPLOAD_FAIL("文件上传失败");

    private final String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
