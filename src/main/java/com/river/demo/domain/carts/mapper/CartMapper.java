package com.river.demo.domain.carts.mapper;

import com.river.demo.domain.carts.model.dto.CartDescription;
import com.river.demo.domain.carts.model.dto.CartItemDescription;
import com.river.demo.domain.carts.model.entity.Cart;
import com.river.demo.domain.carts.model.entity.CartItem;

public class CartMapper {

    public static CartDescription toCartDescription(Cart cart) {
        return new CartDescription(
                cart.getCode(),
                cart.getCartItems()
                        .stream()
                        .map(CartMapper::toCartItemDescription)
                        .toList(),
                cart.getTotalPrice()
        );
    }

    public static CartItemDescription toCartItemDescription(CartItem cartItem) {
        return new CartItemDescription(
                cartItem.getCode(),
                cartItem.getProductCode(),
                cartItem.getProductName(),
                cartItem.getProductPrice(),
                cartItem.getQuantity(),
                cartItem.getCreatedAt()
        );
    }

}
