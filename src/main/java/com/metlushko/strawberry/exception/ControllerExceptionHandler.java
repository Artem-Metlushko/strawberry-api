package com.metlushko.strawberry.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.metlushko.strawberry.controller")
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleExceptions(Exception exception, HttpServletRequest request) {
        return "/ExceptionPage";
    }

}
