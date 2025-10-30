package com.river.accountservice.service.impl;

import com.river.accountservice.mapper.AuthenticationMapper;
import com.river.accountservice.model.dto.AuthenticationDetails;
import com.river.accountservice.service.AccountService;
import com.river.accountservice.service.AuthenticationManager;
import com.river.accountservice.service.TokenProvider;
import com.river.core.model.vo.TokenBody;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
