package com.river.demo.domain.auth.service;

import com.river.demo.domain.accounts.model.entity.Account;
import com.river.demo.domain.accounts.service.AccountService;
import com.river.demo.domain.auth.mapper.AuthenticationMapper;
import com.river.demo.domain.auth.model.dto.AuthenticationDetails;
import com.river.demo.domain.auth.model.vo.TokenBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationManagerImpl implements AuthenticationManager {

    @Value("${custom.jwt.expiration}")
    private Long availableTime;
    private final TokenProvider tokenProvider;

    private final AccountService accountService;

    @Override
    public AuthenticationDetails loadAuthenticationByCode(String accountCode) {
        return AuthenticationMapper.toDetails(accountService.getByAccountCode(accountCode));
    }

    @Override
    public boolean validateToken(String token) {
        return tokenProvider.validate(token);
    }

    @Override
    public TokenBody parseToken(String token) {
        return tokenProvider.parse(token);
    }

    @Override
    public String issueToken(String code) {
        return tokenProvider.issue(availableTime, Map.of("accountCode", code));
    }


}
