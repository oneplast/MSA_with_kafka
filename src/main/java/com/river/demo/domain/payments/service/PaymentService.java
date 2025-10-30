package com.river.demo.domain.payments.service;

import com.river.demo.domain.payments.model.entity.Payment;
import com.river.demo.domain.payments.model.vo.PaymentConfirmRequest;
import com.river.demo.domain.payments.model.dto.PaymentRequest;

public interface PaymentService {

    Payment process(String accountCode, PaymentConfirmRequest request);

    Payment pay(PaymentRequest request);

    Payment refund(String accountCode, PaymentRequest request);

    Payment confirmPayment(PaymentRequest request) throws Exception;

}
