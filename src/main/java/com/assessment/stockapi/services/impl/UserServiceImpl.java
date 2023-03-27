package com.assessment.stockapi.services.impl;

import com.assessment.stockapi.dtos.UserDto;
import com.assessment.stockapi.exceptions.InvalidCredentialException;
import com.assessment.stockapi.exceptions.UserAlreadyExistException;
import com.assessment.stockapi.model.User;
import com.assessment.stockapi.payload.request.LoginRequest;
import com.assessment.stockapi.payload.responses.UserResponse;
import com.assessment.stockapi.repositories.UserRepository;
import com.assessment.stockapi.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public String registerUser(UserDto userDto) {
        log.info("Registering user: {}", userDto.getEmail());
        if (userRepository.existsByEmail(userDto.getEmail())) {
            log.error("User with email: {} already exists", userDto.getEmail());
            throw new UserAlreadyExistException("User with email: " + userDto.getEmail() + " already exists");
        }
        userRepository.save(modelMapper.map(userDto, User.class));
        return "User registered successfully";
    }

    @Override
    public UserResponse loginUser(LoginRequest loginRequest) {
        log.info("Logging in user: {}", loginRequest.getEmail());
        return userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
                .map(user -> {
                    user.setActive(true);
                    userRepository.save(user);
                    return modelMapper.map(user, UserResponse.class);
                })
                .orElseThrow(() -> new InvalidCredentialException("Invalid email or password"));
    }

    @Override
    public String logoutUser(long userId) {
        log.info("Logging out user with id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new InvalidCredentialException("Invalid user id"));
        user.setActive(false);
        userRepository.save(user);
        return "User logged out successfully";
    }
}
