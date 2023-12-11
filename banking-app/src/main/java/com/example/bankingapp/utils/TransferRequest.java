package com.example.bankingapp.utils;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequest {
    @NotNull(message = "Sender's account number shouldn't be empty")
    private Long senderAccountNumber;
    @NotNull(message = "Receiver's account number shouldn't be empty")
    private Long receiverAccountNumber;
    @NotNull(message = "Amount should not be empty")
    @Min(value = 1, message = "Amount should be minimum 1")
    private Double amount;
}
