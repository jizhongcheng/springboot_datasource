package com.windmill.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.windmill.common.msg.ResponseResult;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult<Object> commonException(BusinessException e) {
        e.printStackTrace();
        return ResponseResult.failed(e.getCode(), e.getMessage());
    }


    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseResult<Object> errorHandler(Exception e) {
        e.printStackTrace();
        return ResponseResult.failed(500, "系统内部错误");

    }
}
