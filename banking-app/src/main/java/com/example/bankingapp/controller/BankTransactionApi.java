package com.example.bankingapp.controller;

import com.example.bankingapp.entity.AccountDetails;
import com.example.bankingapp.service.TransactionService;
import com.example.bankingapp.utils.DepositRequest;
import com.example.bankingapp.utils.TransferRequest;
import com.example.bankingapp.utils.WithdrawRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class BankTransactionApi {
    private TransactionService transactionService;

    @Autowired
    public BankTransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PutMapping("/withdraw")
    public AccountDetails withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest) {
        return transactionService.withdraw(withdrawRequest);
    }

    @PutMapping("/deposit")
    public AccountDetails deposit(@Valid @RequestBody DepositRequest depositRequest) {
        return transactionService.deposit(depositRequest);
    }

    @PutMapping("/transfer")
    public Map<String,AccountDetails> transfer(@Valid @RequestBody TransferRequest transferRequest) {
        return transactionService.transfer(transferRequest);
    }
}
