package com.river.demo.domain.auth.model.dto;

public record SignInRequest(
        String username,
        String password
) {
}
