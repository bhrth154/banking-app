package com.example.bankingapp.utils;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepositRequest {
    @NotNull(message = "Account number shouldn't be empty")
    private Long accountNumber;
    @NotNull(message = "Amount should not be empty")
    @Min(value = 1, message = "Amount should be minimum 1")
    private Double amount;
}
