package com.windmill.common.msg;

import lombok.Data;

/**
 * 前后台交互
 *
 * @author LiYanCheng@HF
 * @since 2017-11-21 11:18:03
 */
@Data
public class JsonResult<T> {

//    public static final String SUCCESS = "SUCCESS";
//    public static final String FAILED = "FAILED";

    private boolean success;
    private T data;
    private String message;
    private String statusCode;

    public JsonResult() {

    }

    public JsonResult(boolean success) {
        this.success = success;
    }

    public JsonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public JsonResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
