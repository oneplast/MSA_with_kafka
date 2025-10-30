package com.river.accountservice.api;

import com.river.accountservice.mapper.AccountMapper;
import com.river.accountservice.model.dto.AccountDescription;
import com.river.accountservice.model.dto.AccountDetail;
import com.river.accountservice.model.dto.CreateAccountRequest;
import com.river.accountservice.service.AccountService;
import com.river.core.model.web.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountApiController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<AccountDescription> createAccount(
            @RequestBody @Valid CreateAccountRequest request
    ) {
        return new BaseResponse<>(
                AccountMapper.toDescription(accountService.create(request)),
                "성공적으로 회원가입이 되었습니다."
        );
    }

    @GetMapping("/{code}")
    public BaseResponse<AccountDetail> getAccountDetail(
            @PathVariable String code
    ) {
        return new BaseResponse<>(
                AccountMapper.toDetail(accountService.getByAccountCode(code))
                ,"회원 정보를 성공적으로 불러왔습니다.");
    }


}
