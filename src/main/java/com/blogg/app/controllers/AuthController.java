package com.blogg.app.controllers;

import com.blogg.app.dto.auth.request.RegisterUser;
import com.blogg.app.exceptions.EmailAlreadyExistsException;
import com.blogg.app.exceptions.UserNotCreatedException;
import com.blogg.app.exceptions.UsernameAlreadyTakenException;
import com.blogg.app.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUser request) throws UserNotCreatedException, UsernameAlreadyTakenException, EmailAlreadyExistsException {
        authService.registerUser(request);
        return ResponseEntity.ok("Successfully created new user " + request.getUsername());
    }
}
