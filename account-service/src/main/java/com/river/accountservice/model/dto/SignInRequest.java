package com.river.accountservice.model.dto;

public record SignInRequest(
        String username,
        String password
) {
}
