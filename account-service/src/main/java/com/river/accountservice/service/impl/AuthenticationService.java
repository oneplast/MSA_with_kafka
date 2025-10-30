package com.river.accountservice.service.impl;

import com.river.accountservice.mapper.AuthenticationMapper;
import com.river.accountservice.model.entity.Account;
import com.river.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Account account = accountService.getByUsername(username);

        return AuthenticationMapper.toDetails(account);

    }

}
