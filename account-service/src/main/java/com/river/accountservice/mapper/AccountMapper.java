package com.river.accountservice.mapper;

import com.river.accountservice.model.dto.AccountDescription;
import com.river.accountservice.model.dto.AccountDetail;
import com.river.accountservice.model.entity.Account;

public class AccountMapper {

    public static AccountDescription toDescription(Account account) {
        return new AccountDescription(account.getCode(), account.getUsername());
    }

    public static AccountDetail toDetail(Account account) {
        return new AccountDetail(account.getUsername(), account.getEmail());
    }

}
