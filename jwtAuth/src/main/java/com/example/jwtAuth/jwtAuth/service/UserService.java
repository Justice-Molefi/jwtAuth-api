package com.example.jwtAuth.jwtAuth.service;

import com.example.jwtAuth.jwtAuth.config.JwtConfig;
import com.example.jwtAuth.jwtAuth.dto.AuthRequest;
import com.example.jwtAuth.jwtAuth.dto.AuthResponse;
import com.example.jwtAuth.jwtAuth.exception.UserExistsException;
import com.example.jwtAuth.jwtAuth.model.Role;
import com.example.jwtAuth.jwtAuth.model.User;
import com.example.jwtAuth.jwtAuth.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;
    private final JwtDecoder jwtDecoder;
    private final AuthenticationManager authenticationManager;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtConfig jwtConfig, JwtDecoder jwtDecoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.jwtDecoder = jwtDecoder;
    }

    public void register(@Valid AuthRequest registerRequest){
        Optional<User> dbUser = userRepository.findByEmail(registerRequest.email().trim());

        if(dbUser.isPresent()){
           throw new UserExistsException("Something went wrong, Try again later");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(Role.MEMBER);

        User user = User.builder()
                .uuid(UUID.randomUUID())
                .email(registerRequest.email().trim())
                .password(passwordEncoder.encode(registerRequest.password()))
                .roles(roles)
                .build();

        userRepository.save(user);

    }

    public AuthResponse login(@Valid AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.email().trim(), authRequest.password()));

        if(authentication.isAuthenticated()){
            String token = jwtConfig.generateToken(authentication);
            return AuthResponse.builder()
                    .accessToken(token)
                    .build();
        }

        return AuthResponse.builder().accessToken("").build();
    }

    public void verifyToken(String token) {
        jwtDecoder.decode(token);
    }

}
