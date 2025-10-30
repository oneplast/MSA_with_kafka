package com.river.accountservice.mapper;

import com.river.accountservice.model.dto.AuthenticationDetails;
import com.river.accountservice.model.entity.Account;

public class AuthenticationMapper {

    public static AuthenticationDetails toDetails(Account account) {
        return new AuthenticationDetails(account.getCode(), account.getUsername(), account.getPassword(), account.getRoles());
    }

}
