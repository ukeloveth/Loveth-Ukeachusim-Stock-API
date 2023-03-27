package com.assessment.stockapi.controllers;

import com.assessment.stockapi.dtos.UserDto;
import com.assessment.stockapi.payload.request.LoginRequest;
import com.assessment.stockapi.payload.responses.MessageResponse;
import com.assessment.stockapi.payload.responses.UserResponse;
import com.assessment.stockapi.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody UserDto userDto) {
        log.info("Registering user");
        return new ResponseEntity<>(new MessageResponse(userService.registerUser(userDto)), CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Logging in user");
        return new ResponseEntity<>(userService.loginUser(loginRequest), OK);
    }

    @PostMapping("/logout/{userId}")
    public ResponseEntity<MessageResponse> logoutUser(@PathVariable long userId) {
        log.info("Logging out user");
        return new ResponseEntity<>(new MessageResponse(userService.logoutUser(userId)), OK);
    }
}
