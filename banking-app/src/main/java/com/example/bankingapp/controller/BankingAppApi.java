package com.example.bankingapp.controller;

import com.example.bankingapp.entity.AccountDetails;
import com.example.bankingapp.entity.User;
import com.example.bankingapp.service.BankingAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banking")
public class BankingAppApi {
    public BankingAppService bankingAppService;

    @Autowired
    public BankingAppApi(BankingAppService bankingAppService) {
        this.bankingAppService = bankingAppService;
    }

    @PostMapping("/user/create")
    public AccountDetails createUser(@RequestBody User user) {
        return bankingAppService.createOrUpdate(user);
    }

    @GetMapping("/user/get")
    private List<AccountDetails> findAll() {
        return bankingAppService.findAll();
    }

    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable("id") long userId) {
        return bankingAppService.findUser(userId);
    }

    @DeleteMapping("/user/delete")
    public List<AccountDetails> deleteAll() {
        return bankingAppService.deleteAll();
    }
}
