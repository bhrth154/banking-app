package com.example.bankingapp.service;

import com.example.bankingapp.dao.AccountDetailsRepository;
import com.example.bankingapp.entity.AccountDetails;
import com.example.bankingapp.entity.AccountType;
import com.example.bankingapp.exceptions.InsufficientBalanceException;
import com.example.bankingapp.exceptions.NoAccountFoundException;
import com.example.bankingapp.exceptions.SameAccountException;
import com.example.bankingapp.utils.DepositRequest;
import com.example.bankingapp.utils.TransferRequest;
import com.example.bankingapp.utils.WithdrawRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionService {
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    public TransactionService(AccountDetailsRepository accountDetailsRepository) {
        this.accountDetailsRepository = accountDetailsRepository;
    }

    public AccountDetails withdraw(WithdrawRequest withdrawRequest) {
        if (!accountDetailsRepository.existsById(withdrawRequest.getAccountNumber())) {
            throw new NoAccountFoundException("No account found with given account number");
        }
        AccountDetails accountDetails = accountDetailsRepository.findById(withdrawRequest.getAccountNumber()).get();
        validateBalanceOnWithdraw(accountDetails, accountDetails.getBalance() - withdrawRequest.getAmount());
        double newAccountBalance = accountDetails.getBalance() - withdrawRequest.getAmount();
        accountDetails.setBalance(newAccountBalance);
        accountDetailsRepository.saveAndFlush(accountDetails);
        return accountDetails;
    }

    private static void validateBalanceOnWithdraw(AccountDetails accountDetails, Double newAccountBalance) {
        if (accountDetails.getAccountType().equals(AccountType.CURRENT)) {
            if (accountDetails.getBalance()==500.00) {
                throw new InsufficientBalanceException("Can't withdraw amount, only minimum balance is there in the account");
            }
            if (newAccountBalance < 500.00) {
                throw new InsufficientBalanceException("Accepted withdraw amount is only " + (accountDetails.getBalance() - 500.00));
            }
        } else if (accountDetails.getAccountType().equals(AccountType.SAVINGS)) {
            if (accountDetails.getBalance()==0.00) {
                throw new InsufficientBalanceException("Can't withdraw amount, only minimum balance is there in the account");
            }
            if (newAccountBalance < 0) {
                throw new InsufficientBalanceException("Accepted withdraw amount is only " + accountDetails.getBalance());
            }
        }
    }

    public AccountDetails deposit(DepositRequest depositRequest) {
        if (!accountDetailsRepository.existsById(depositRequest.getAccountNumber())) {
            throw new NoAccountFoundException("No account found with given account number");
        }
        AccountDetails accountDetails = accountDetailsRepository.findById(depositRequest.getAccountNumber()).get();
        Double newAccountBalance = accountDetails.getBalance() + depositRequest.getAmount();
        accountDetails.setBalance(newAccountBalance);
        accountDetailsRepository.saveAndFlush(accountDetails);
        return accountDetails;
    }

    public Map<String, AccountDetails> transfer(TransferRequest transferRequest) {
        if (transferRequest.getSenderAccountNumber().equals(transferRequest.getReceiverAccountNumber())) {
            throw new SameAccountException("Both accounts should not be the same");
        }
        if (!accountDetailsRepository.existsById(transferRequest.getSenderAccountNumber())) {
            throw new NoAccountFoundException("No account found with given sender's account number");
        }
        if (!accountDetailsRepository.existsById(transferRequest.getReceiverAccountNumber())) {
            throw new NoAccountFoundException("No account found with given receiver's account number");
        }

        AccountDetails senderAccount = accountDetailsRepository.findById(transferRequest.getSenderAccountNumber()).get();
        validateBalanceOnWithdraw(senderAccount, senderAccount.getBalance() - transferRequest.getAmount());
        double newSenderBalance = senderAccount.getBalance() - transferRequest.getAmount();
        senderAccount.setBalance(newSenderBalance);

        AccountDetails receiverAccount = accountDetailsRepository.findById(transferRequest.getReceiverAccountNumber()).get();
        double newReceiverBalance = receiverAccount.getBalance() + transferRequest.getAmount();
        receiverAccount.setBalance(newReceiverBalance);

        accountDetailsRepository.saveAndFlush(senderAccount);
        accountDetailsRepository.saveAndFlush(receiverAccount);

        Map<String, AccountDetails> accountsAfterTransfer = new HashMap<>(2);
        accountsAfterTransfer.put("Sender's account", senderAccount);
        accountsAfterTransfer.put("Receiver's account", receiverAccount);

        return accountsAfterTransfer;
    }
}
