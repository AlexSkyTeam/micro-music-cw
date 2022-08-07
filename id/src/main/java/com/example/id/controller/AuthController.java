package com.example.id.controller;

import com.example.id.dto.*;
import com.example.id.manager.AuthManager;
import com.example.id.security.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthManager manager;

    @PostMapping
    public AuthResponseDTO auth(
            @RequestAttribute final Authentication authentication,
            @Valid @RequestBody AuthRequestDTO requestDTO
    ) {
        return manager.auth(authentication, requestDTO);
    }

    @PostMapping("/verify")
    public VerificationResponseDTO verify(
            @RequestAttribute final Authentication authentication,
            @Valid @RequestBody VerificationRequestDTO requestDTO
    ) {
        return manager.verify(authentication, requestDTO);
    }
}
