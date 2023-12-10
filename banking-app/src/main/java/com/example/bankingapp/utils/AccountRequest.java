package com.example.bankingapp.utils;

import com.example.bankingapp.entity.AccountDetails;
import com.example.bankingapp.entity.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequest {
    @NotNull(message = "User id is required to open an account")
    private Long userId;
    @NotNull(message = "Account type is required to open an account")
    private AccountType accountType;
    private double initialDeposit;

    public AccountDetails toAccountDetails() {
        return new AccountDetails(initialDeposit, accountType);
    }
}
