package com.example.jwtAuth.jwtAuth.exception;


import com.example.jwtAuth.jwtAuth.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ZonedDateTime.now(), HttpStatus.BAD_REQUEST, "Bad Request", request.getServletPath());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> handleUserExistException(UserExistsException e, HttpServletRequest request){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ZonedDateTime.now(), HttpStatus.CONFLICT, e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.CONFLICT);
    }
}
