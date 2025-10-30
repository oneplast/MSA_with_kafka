package com.river.demo.domain.deposits.service;

import com.river.demo.domain.deposits.model.entity.Deposit;
import com.river.demo.domain.deposits.model.entity.DepositHistory;

public interface DepositService {

    Deposit save(String accountCode);

    Deposit getByAccountCode(String accountCode);

    boolean hasEnoughBalance(String depositCode, Long amount);

    DepositHistory charge(String accountCode, DepositHistory.DepositHistoryType type, Long amount);
    DepositHistory withdraw(String accountCode, DepositHistory.DepositHistoryType type, Long amount);
    DepositHistory refund(String accountCode, DepositHistory.DepositHistoryType type, Long amount);

}
