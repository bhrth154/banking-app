package com.example.bankingapp.controller;

import com.example.bankingapp.entity.User;
import com.example.bankingapp.service.BankingAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/banking")
public class BankingAppApi {
    public BankingAppService bankingAppService;

    @Autowired
    public BankingAppApi(BankingAppService bankingAppService) {
        this.bankingAppService = bankingAppService;
    }

    @PostMapping("/user/create")
    public User createUser(@RequestBody User user) {
        return bankingAppService.createOrUpdate(user);
    }

    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable("id") long userId) {
        return bankingAppService.findUser(userId);
    }
}
