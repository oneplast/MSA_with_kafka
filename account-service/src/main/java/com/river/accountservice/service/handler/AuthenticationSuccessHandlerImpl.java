package com.river.accountservice.service.handler;

import com.river.accountservice.model.dto.AuthenticationDetails;
import com.river.accountservice.service.AuthenticationManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final AuthenticationManager authenticationManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication)
            throws IOException, ServletException {

        AuthenticationDetails details = (AuthenticationDetails) authentication.getPrincipal();
        String token = authenticationManager.issueToken(details.getAccountCode());

        response.addHeader("token", token);

        // TEMP
        response.addCookie(
                new Cookie("token", token)
        );

        // 리디렉션

    }

}
