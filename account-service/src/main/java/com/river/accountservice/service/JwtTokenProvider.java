package com.river.accountservice.service;

import com.river.core.model.vo.TokenBody;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtTokenProvider implements TokenProvider {

    // ConfigurationPropertiesScan 라는 선택지도 있음
    @Value("${custom.jwt.secret-key}")
    private String appKey;

    @Override
    public String issue(Long validateTime, Map<String, Object> claims) {
        JwtBuilder builder = Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + validateTime))
                .signWith(getSecretKey(), Jwts.SIG.HS256);

        for (Map.Entry<String, Object> claimEntry : claims.entrySet()) {
            builder.claim(claimEntry.getKey(), claimEntry.getValue());
        }

        return builder.compact();
    }

    @Override
    public boolean validate(String token) {
        try {
            getClaims(token);
            return true;
        } catch ( JwtException e ) {
            log.info("Invalid JWT Token was detected: {}  msg : {}", token ,e.getMessage());
            System.out.println("Invalid Token signature");
        } catch ( IllegalStateException e ) {
            log.info("JWT claims String is empty: {}  msg : {}", token ,e.getMessage());
        } catch ( Exception e ) {
            log.error("an error raised from validating token : {}  msg : {}", token ,e.getMessage());
        }

        return false;
    }

    @Override
    public Map<String, Object> getClaims(String token) {

        Jws<Claims> parsed = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token);

        return parsed.getPayload();
    }

    @Override
    public TokenBody parse(String token) {
        return new TokenBody(
                getClaims(token).get("accountCode").toString()
        );
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(appKey.getBytes());
    }
}
