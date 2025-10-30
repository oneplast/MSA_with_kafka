package com.river.accountservice.service.impl;

import com.river.accountservice.model.dto.CreateAccountRequest;
import com.river.accountservice.model.entity.Account;
import com.river.accountservice.repository.AccountRepository;
import com.river.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    // TEMP
//    private final CartService cartService;
//    private final DepositService depositService;

    @Override
    public Account create(CreateAccountRequest request) {

        Account account = Account.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .build();

//        Cart cart = cartService.save(account.getCode());
//        account.setCartCode(cart.getCode());
//
//        Deposit deposit = depositService.save(account.getCode());
//        account.setDepositCode(deposit.getCode());

        return accountRepository.save(account);

    }

    @Override
    @Transactional(readOnly = true)
    public Account getByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 존재하지 않습니다."));
    }

    @Override
    @Transactional(readOnly = true)
    public Account getByAccountCode(String accountCode) {
        return accountRepository.findByCode(accountCode)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 존재하지 않습니다."));
    }

}
