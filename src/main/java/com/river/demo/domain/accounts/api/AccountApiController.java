package com.river.demo.domain.accounts.api;

import com.river.core.model.web.BaseResponse;
import com.river.demo.domain.accounts.mapper.AccountMapper;
import com.river.demo.domain.accounts.model.dto.AccountDescription;
import com.river.demo.domain.accounts.model.dto.AccountDetail;
import com.river.demo.domain.accounts.model.dto.CreateAccountRequest;
import com.river.demo.domain.accounts.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
