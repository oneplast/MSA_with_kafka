package com.river.demo.domain.orders.api;

import com.river.core.model.web.BaseResponse;
import com.river.demo.domain.orders.mapper.OrderMapper;
import com.river.demo.domain.orders.model.dto.OrderDescription;
import com.river.demo.domain.orders.model.entity.Orders;
import com.river.demo.domain.orders.model.vo.CreateOrderRequest;
import com.river.demo.domain.orders.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping
    public BaseResponse<OrderDescription> create(
//            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @RequestHeader("X-CODE") String accountCode,
            @RequestBody @Valid CreateOrderRequest request
    ) {

        Orders order = orderService.createOrder(accountCode, request);

        return new BaseResponse<>(
                OrderMapper.toOrderDescription(order),
                "성공적으로 주문되었습니다."
        );

    }

    @GetMapping("/{orderCode}")
    public BaseResponse<OrderDescription> getDescription(
//            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @RequestHeader("X-CODE") String accountCode,
            @PathVariable String orderCode
    ) {

        Orders order = orderService.getOrder(accountCode, orderCode);

        return new BaseResponse<>(
                OrderMapper.toOrderDescription(order),
                "주문이 성공적으로 조회되었습니다."
        );

    }


}
