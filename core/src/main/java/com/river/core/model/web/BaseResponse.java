package com.river.core.model.web;

public record BaseResponse<T>(
        T data,
        String message
) {
}
