package com.metlushko.strawberry.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.metlushko.strawberry.restcontroller")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}
