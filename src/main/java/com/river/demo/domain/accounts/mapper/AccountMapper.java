package com.river.demo.domain.accounts.mapper;

import com.river.demo.domain.accounts.model.dto.AccountDescription;
import com.river.demo.domain.accounts.model.dto.AccountDetail;
import com.river.demo.domain.accounts.model.entity.Account;

public class AccountMapper {

    public static AccountDescription toDescription(Account account) {
        return new AccountDescription(account.getCode(), account.getUsername());
    }

    public static AccountDetail toDetail(Account account) {
        return new AccountDetail(account.getUsername(), account.getEmail());
    }

}
