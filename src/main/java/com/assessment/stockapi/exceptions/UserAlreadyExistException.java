package com.assessment.stockapi.exceptions;

import jakarta.validation.constraints.NotBlank;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(@NotBlank(message = "Email is required") String s) {
        super(s);
    }
}
