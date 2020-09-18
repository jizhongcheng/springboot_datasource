package com.windmill.common.web;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.web.bind.annotation.PostMapping;

public class BaseErrorController extends BasicErrorController implements ErrorController {
    public BaseErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    public String getErrorPath() {
        return "/error";
    }

    @PostMapping(produces = {"text/html"})
    public String errorHtml() {
        return "error/error";
    }
}
