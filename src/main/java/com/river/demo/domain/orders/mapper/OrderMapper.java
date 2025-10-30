package com.river.demo.domain.orders.mapper;

import com.river.demo.domain.orders.model.dto.OrderDescription;
import com.river.demo.domain.orders.model.dto.OrderItemDescription;
import com.river.demo.domain.orders.model.entity.OrderItem;
import com.river.demo.domain.orders.model.entity.Orders;
import org.hibernate.query.Order;

public class OrderMapper {

    public static OrderDescription toOrderDescription(Orders order) {
        return new OrderDescription(
                order.getCode(),
                order.getOrderState(),
                order.getOrderItems()
                        .stream()
                        .map(OrderMapper::toOrderItemDescription)
                        .toList(),
                order.getCreatedAt(),
                order.getTotalPrice()
        );
    }

    public static OrderItemDescription toOrderItemDescription(OrderItem orderItem) {
        return new OrderItemDescription(
                orderItem.getProductCode(),
                orderItem.getProductName(),
                orderItem.getProductPrice(),
                orderItem.getQuantity()
        );
    }

}
