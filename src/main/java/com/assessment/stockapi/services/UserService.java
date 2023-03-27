package com.assessment.stockapi.services;

import com.assessment.stockapi.dtos.UserDto;
import com.assessment.stockapi.payload.request.LoginRequest;
import com.assessment.stockapi.payload.responses.UserResponse;

public interface UserService {
    String registerUser(UserDto userDto);
    UserResponse loginUser(LoginRequest loginRequest);
    String logoutUser(long userId);
}
