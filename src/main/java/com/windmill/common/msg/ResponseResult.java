package com.windmill.common.msg;

import lombok.Data;

@Data
public class ResponseResult<T> {

    private int code;

    private String message;

    private T data;

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(int code, String message) {
        this(code, message, null);
    }

    public ResponseResult(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage());
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<T>(ResultCode.OK);
    }

    public static <T> ResponseResult<T> failed(int code, String message, T data) {
        return new ResponseResult<T>(code, message, data);
    }

    public static <T> ResponseResult<T> failed(int code, String message) {
        return new ResponseResult<T>(code, message, null);
    }
}
