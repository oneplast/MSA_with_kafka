package com.river.demo.domain.orders.model.entity;

import com.river.core.model.persistence.BaseEntity;
import com.river.demo.domain.orders.model.vo.OrderState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders extends BaseEntity {

    @Setter
    private String accountCode;

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderState orderState = OrderState.CREATED;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Orders(String accountCode) {
        this.accountCode = accountCode;
    }

    public Long getTotalPrice() {
        return orderItems.stream()
                .mapToLong(i -> i.getProductPrice() * i.getQuantity())
                .sum();
    }

    public void addItem(OrderItem item) {
        this.orderItems.add(item);
        item.setOrder(this);
    }

    public boolean canPay() {
        return this.orderState == OrderState.CREATED;
    }

    public void cancel() {
        this.orderState = OrderState.CANCELLED;
    }

    public void complete() {
        this.orderState = OrderState.PAID;
    }

}
