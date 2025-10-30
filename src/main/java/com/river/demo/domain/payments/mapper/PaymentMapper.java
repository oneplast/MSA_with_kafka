package com.river.demo.domain.payments.mapper;

import com.river.demo.domain.payments.model.entity.Payment;
import com.river.demo.domain.payments.model.vo.PaymentDescription;

public class PaymentMapper {

    public static PaymentDescription toDescription(Payment payment) {
        return new PaymentDescription(
                payment.getOrderCode(),
                payment.getCode()
        );
    }

}
