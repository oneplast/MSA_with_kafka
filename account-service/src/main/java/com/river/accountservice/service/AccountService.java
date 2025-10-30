package com.river.accountservice.service;

import com.river.accountservice.model.dto.CreateAccountRequest;
import com.river.accountservice.model.entity.Account;

public interface AccountService {

    Account create(CreateAccountRequest request);
    Account getByUsername(String username);
    Account getByAccountCode(String accountCode);

}
