package com.river.demo.domain.auth.service;

import com.river.demo.domain.auth.model.vo.TokenBody;

import java.util.Map;

public interface TokenProvider {
    String issue(Long validateTime, Map<String, Object> claims);
    boolean validate(String t);
    TokenBody parse(String t);
    Map<String, Object> getClaims(String t);
}
