package com.example.potteryclub.api;

import com.example.potteryclub.AuthLoginResponse;
import com.example.potteryclub.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.openapitools.model.AuthLoginPost200Response;
import org.openapitools.model.AuthRegisterPostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Authentication", description = "Auth API")
@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Void> register(
            @RequestBody AuthRegisterPostRequest request
    ) {
        userService.register(request.getEmail(), request.getPassword());
        return ResponseEntity.status(201).build();
    }
    @PostMapping("/auth/login")
    public ResponseEntity<AuthLoginResponse> login(
            @RequestBody AuthRegisterPostRequest request
    ) {
        AuthLoginResponse response = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }
}