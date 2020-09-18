package com.windmill.common.service;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ServiceException() {
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
