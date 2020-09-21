package com.ktm.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultDTO implements Serializable {

    String message;
    Integer code;

    public ResultDTO(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public static ResultDTO fail(String message, Integer code) {
        return new ResultDTO(message, code);
    }
    public static ResultDTO ok(String message, Integer code) {
        return new ResultDTO(message, code);
    }

    public static ResultDTO ok() {
        return new ResultDTO("success", 200);
    }

    public static ResultDTO fail(String message) {

        return new ResultDTO(message, 500);
    }

    public static ResultDTO fail() {
        return fail("系统内部错误");
    }
}
