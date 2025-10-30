package com.river.demo.common.model.web;

public record PageResponse<T>(
        int pageNum,
        int totalPages,
        int pageSize,
        boolean hasNext,
        T contents
) {
}
