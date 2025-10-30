package com.river.demo.domain.auth.mapper;

import com.river.demo.domain.auth.model.dto.AuthenticationDetails;
import com.river.demo.domain.accounts.model.entity.Account;

public class AuthenticationMapper {

    public static AuthenticationDetails toDetails(Account account) {
        return new AuthenticationDetails(account.getCode(), account.getUsername(), account.getPassword(), account.getRoles());
    }

}
