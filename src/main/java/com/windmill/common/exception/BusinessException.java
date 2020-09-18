package com.windmill.common.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

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

}
