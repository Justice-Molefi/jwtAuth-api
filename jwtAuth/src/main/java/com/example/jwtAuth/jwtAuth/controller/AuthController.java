package com.example.jwtAuth.jwtAuth.controller;

import com.example.jwtAuth.jwtAuth.dto.AuthRequest;
import com.example.jwtAuth.jwtAuth.dto.AuthResponse;
import com.example.jwtAuth.jwtAuth.dto.TokenRequest;
import com.example.jwtAuth.jwtAuth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid AuthRequest authRequest){
        userService.register(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successful");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        AuthResponse authResponse = userService.login(authRequest);
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @PostMapping("/verifyToken")
    public ResponseEntity<String> verifyToken(@RequestBody TokenRequest tokenRequest){
        try{
            userService.verifyToken(tokenRequest.token());
            return ResponseEntity.status(HttpStatus.OK).body("Token Valid");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }

    }

}
