package com.river.accountservice.service;

import com.river.accountservice.model.dto.AuthenticationDetails;
import com.river.core.model.vo.TokenBody;

public interface AuthenticationManager {

    AuthenticationDetails loadAuthenticationByCode(String accountCode);

    boolean validateToken(String token);
    TokenBody parseToken(String token);
    String issueToken(String code);


}
