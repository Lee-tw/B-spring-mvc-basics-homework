package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.controller.dto.AccountInputDTO;
import com.thoughtworks.capacity.gtb.mvc.errorCode.ErrorCode;
import com.thoughtworks.capacity.gtb.mvc.exception.AccountException;
import com.thoughtworks.capacity.gtb.mvc.model.Account;
import com.thoughtworks.capacity.gtb.mvc.service.AccountService;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@AllArgsConstructor
@Validated
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@Valid @RequestBody AccountInputDTO accountInputDTO) {
        Account account = accountInputDTO.toAccount();
        if (accountService.hasAccount(account.getName()))
            throw new AccountException(ErrorCode.USER_EXISTS);
        int id = accountService.getAccounts().size();
        account.setId(id + 1);
        accountService.createAccount(account);
    }

    @GetMapping("/login")
    public ResponseEntity<Account> login(@RequestParam("name") @Length(min = 5, max = 12, message = "用户名不合法") String name,
                        @RequestParam("password") @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)(?![0-9_]+$)(?![0-9a-zA-Z]+$)(?![a-zA-Z_]+$)[0-9A-Za-z_]{5,12}$", message = "密码不合法")
                                String password) {
        Account account = accountService.getAccountByName(name);
        if (account == null || !password.equals(account.getPassword()))
            throw new AccountException(ErrorCode.USER_NOT_EXIST_ERROR);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }
}
