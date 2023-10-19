package com.demo.demoApi.Exception;

public class BadRequestException extends RuntimeException{
public BadRequestException(String message) {
        super(message);
    }
}
