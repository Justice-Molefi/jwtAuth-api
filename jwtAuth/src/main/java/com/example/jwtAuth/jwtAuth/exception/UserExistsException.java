package com.example.jwtAuth.jwtAuth.exception;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String message){
        super(message);
    }

    public UserExistsException(String message,Throwable throwable){
        super(message, throwable);
    }
}
