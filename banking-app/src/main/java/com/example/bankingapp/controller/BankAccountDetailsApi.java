package com.example.bankingapp.controller;

import com.example.bankingapp.entity.AccountDetails;
import com.example.bankingapp.service.AccountDetailService;
import com.example.bankingapp.utils.AccountRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/account")
public class BankAccountDetailsApi {
    private AccountDetailService accountDetailService;

    @Autowired
    public BankAccountDetailsApi(AccountDetailService accountDetailService) {
        this.accountDetailService = accountDetailService;
    }

    @PostMapping("/create")
    public AccountDetails createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return accountDetailService.createAccount(accountRequest);
    }

    @GetMapping("/read")
    public List<AccountDetails> readAllAccounts() {
        return accountDetailService.getAllAccounts();
    }

    @GetMapping("/read/{account_id}")
    public AccountDetails readAccountById(@PathVariable("account_id") long accountId) {
        return accountDetailService.getAccountById(accountId);
    }

    @GetMapping("/read/user/{user_id}")
    public Set<AccountDetails> readAccountsByUserId(@PathVariable("user_id") long userId) {
        return accountDetailService.getAccountsByUserId(userId);
    }

    @DeleteMapping("/delete")
    public List<AccountDetails> deleteAllAccounts() {
        return accountDetailService.deleteAllAccounts();
    }

    @DeleteMapping("/delete/{account_id}")
    public AccountDetails deleteAccountById(@PathVariable("account_id") long accountId) {
        return accountDetailService.deleteAccountById(accountId);
    }

    @DeleteMapping("/delete/user/{user_id}")
    public Set<AccountDetails> deleteAccountsByUserId(@PathVariable("user_id") long userId) {
        return accountDetailService.deleteAccountsByUserId(userId);
    }
}
