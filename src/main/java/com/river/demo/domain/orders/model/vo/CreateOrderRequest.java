package com.river.demo.domain.orders.model.vo;

import java.util.List;

public record CreateOrderRequest(
        List<String> cartItemCodes
) {
}
