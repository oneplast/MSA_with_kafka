package com.river.demo.domain.carts.api;

import com.river.core.model.web.BaseResponse;
import com.river.demo.domain.carts.mapper.CartMapper;
import com.river.demo.domain.carts.model.dto.CartDescription;
import com.river.demo.domain.carts.model.dto.CartItemDescription;
import com.river.demo.domain.carts.model.entity.Cart;
import com.river.demo.domain.carts.model.entity.CartItem;
import com.river.demo.domain.carts.model.vo.CreateCartItemRequest;
import com.river.demo.domain.carts.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartApiController {

    private final CartService cartService;

    @GetMapping
    public BaseResponse<CartDescription> getDescription(
//            @AuthenticationPrincipal(expression = "accountCode") String accountCode
            @RequestHeader("X-CODE") String accountCode
    ) {
        Cart cart = cartService.getByAccountCode(accountCode);
        return new BaseResponse<>(
                CartMapper.toCartDescription(cart),
                "장바구니를 성공적으로 조회하였습니다"
        );
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CartItemDescription> appendItem(
//            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @RequestHeader("X-CODE") String accountCode,
            @RequestBody @Valid CreateCartItemRequest request
    ) {
        CartItem cartItem = cartService.appendItem(accountCode, request);
        return new BaseResponse<>(
                CartMapper.toCartItemDescription(cartItem),
                "장바구니에 항목이 성공적으로 추가되었습니다."
        );
    }

    @DeleteMapping("/items/{itemCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponse<Void> deleteItem(
//            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @RequestHeader("X-CODE") String accountCode,
            @PathVariable String itemCode
    ) {
        cartService.deleteItem(accountCode, itemCode);
        return new BaseResponse<>(
                null,
                "장바구니에 항목이 성공적으로 삭제되었습니다."
        );
    }

    @PatchMapping("/items/{itemCode}/increase")
    public BaseResponse<CartItemDescription> increaseQuantity(
//            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @RequestHeader("X-CODE") String accountCode,
            @PathVariable String itemCode
    ) {
        CartItem cartItem = cartService.increaseQuantity(accountCode, itemCode);
        return new BaseResponse<>(
                CartMapper.toCartItemDescription(cartItem),
                "장바구니 항목 갯수가 성공적으로 증가하였습니다."
        );
    }

    @PatchMapping("/items/{itemCode}/decrease")
    public BaseResponse<CartItemDescription> decreaseQuantity(
//            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @RequestHeader("X-CODE") String accountCode,
            @PathVariable String itemCode
    ) {
        CartItem cartItem = cartService.decreaseQuantity(accountCode, itemCode);
        return new BaseResponse<>(
                CartMapper.toCartItemDescription(cartItem),
                "장바구니 항목 갯수가 성공적으로 감소되었습니다."
        );
    }




}
