package com.example.jwtAuth.jwtAuth.dto;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiErrorResponse(ZonedDateTime timeStamp, HttpStatus status, String message, String path) {
}
