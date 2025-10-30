package com.river.demo.domain.payments.api;

import com.river.demo.common.model.web.BaseResponse;
import com.river.demo.domain.payments.mapper.PaymentMapper;
import com.river.demo.domain.payments.model.dto.PaymentRequest;
import com.river.demo.domain.payments.model.entity.Payment;
import com.river.demo.domain.payments.model.vo.DepositPaymentRequest;
import com.river.demo.domain.payments.model.vo.PaymentConfirmRequest;
import com.river.demo.domain.payments.model.vo.PaymentDescription;
import com.river.demo.domain.payments.model.vo.PaymentType;
import com.river.demo.domain.payments.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentApiController {

    private final PaymentService paymentService;

    @PostMapping("/process")
    public BaseResponse<PaymentDescription> processPayment(
            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @RequestBody @Valid PaymentConfirmRequest request
    ) {

        Payment payment = paymentService.process(accountCode, request);

        return new BaseResponse<>(
                PaymentMapper.toDescription(payment),
                "결제를 성공적으로 처리하였습니다."
        );

    }

    @PostMapping("/toss")
    public BaseResponse<PaymentDescription> tossPayment(
            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @RequestBody @Valid PaymentConfirmRequest request
    ) {

        Payment payment = paymentService.pay(
                new PaymentRequest(accountCode, PaymentType.TOSS_PAYMENT, request.paymentKey(), request.orderCode(), request.amount())
        );

        return new BaseResponse<>(
                PaymentMapper.toDescription(payment),
                "결제를 성공적으로 처리하였습니다."
        );
    }

    @PostMapping("/deposit")
    public BaseResponse<PaymentDescription> depositPayment(
            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @RequestBody @Valid DepositPaymentRequest request
    ) {

        Payment payment = paymentService.pay(
                new PaymentRequest(accountCode, PaymentType.DEPOSIT, null, request.orderCode(), request.amount())
        );

        return new BaseResponse<>(
                PaymentMapper.toDescription(payment),
                "결제를 성공적으로 처리하였습니다."
        );

    }


}
