package com.river.demo.domain.carts.service;

import com.river.demo.domain.carts.model.entity.Cart;
import com.river.demo.domain.carts.model.entity.CartItem;
import com.river.demo.domain.carts.model.vo.CreateCartItemRequest;

import java.util.List;

public interface CartService {

    Cart save(String accountCode);

    Cart getByAccountCode(String accountCode);

    CartItem getItem(String accountCode, String cartItemCode);
    List<CartItem> getItemsByCodes(List<String> cartItemCodes);

    CartItem appendItem(String accountCode, CreateCartItemRequest request);

    CartItem increaseQuantity(String accountCode, String cartItemCode);
    CartItem decreaseQuantity(String accountCode, String cartItemCode);

    void deleteItem(String accountCode, String cartItemCode);

    void deleteItemsByCodes(List<String> cartItemCodes);

}
