package com.winterchen.exception;

/**
 *
 * 异常信息模板
 * @author Micity
 * @since 2018/9/7 0001
 *
 */
public class ErrorResponseEntity {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public ErrorResponseEntity(){}
    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
