package com.example.bankingapp.service;

import com.example.bankingapp.dao.AccountDetailsRepository;
import com.example.bankingapp.dao.UserRepository;
import com.example.bankingapp.entity.AccountDetails;
import com.example.bankingapp.entity.AccountType;
import com.example.bankingapp.exceptions.NoAccountFoundException;
import com.example.bankingapp.exceptions.NoAccountExistsExecption;
import com.example.bankingapp.exceptions.UnsufficientBalanceException;
import com.example.bankingapp.exceptions.UserNotFoundException;
import com.example.bankingapp.utils.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AccountDetailService {
    private UserRepository userRepository;
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    public AccountDetailService(UserRepository userRepository, AccountDetailsRepository accountDetailsRepository) {
        this.userRepository = userRepository;
        this.accountDetailsRepository = accountDetailsRepository;
    }


    public AccountDetails createAccount(AccountRequest accountRequest) {
        if (!userRepository.existsById(accountRequest.getUserId())) {
            throw new UserNotFoundException("No user exists with given user id");
        }
        if (accountRequest.getAccountType().equals(AccountType.CURRENT)) {
            if (accountRequest.getInitialDeposit() < 500.00) {
                throw new UnsufficientBalanceException("Initial deposit for current account is 500.00");
            }
        }
        AccountDetails accountDetails = accountRequest.toAccountDetails();
        accountDetails.setUser(userRepository.findById(accountRequest.getUserId()).get());
        return accountDetailsRepository.saveAndFlush(accountDetails);
    }

    public Set<AccountDetails> getAccountsByUserId(long userId) {
        if (accountDetailsRepository.findByUserId(userId).isEmpty()) {
            throw new NoAccountFoundException("No accounts found for the given user userId");
        }
        return accountDetailsRepository.findByUserId(userId);
    }

    public List<AccountDetails> getAllAccounts() {
        if (accountDetailsRepository.findAll().isEmpty()) {
            throw new NoAccountExistsExecption("No accounts found");
        }
        return accountDetailsRepository.findAll();
    }

    public AccountDetails getAccountById(long accountId) {
        if (!accountDetailsRepository.existsById(accountId)) {
            throw new NoAccountFoundException(("No account found with given account id"));
        }
        return accountDetailsRepository.findById(accountId).get();
    }

    public Set<AccountDetails> deleteAccountsByUserId(long userId) {
        if (accountDetailsRepository.findByUserId(userId).isEmpty()) {
            throw new NoAccountFoundException("No accounts found for the given user id");
        }

        Set<AccountDetails> accountsByUserId = accountDetailsRepository.findByUserId(userId);
        accountDetailsRepository.deleteByUserId(userId);
        return accountsByUserId;
    }

    public List<AccountDetails> deleteAllAccounts() {
        if (accountDetailsRepository.findAll().isEmpty()) {
            throw new NoAccountExistsExecption("No accounts found");
        }
        List<AccountDetails> allAccounts = accountDetailsRepository.findAll();
        accountDetailsRepository.deleteAll();
        return allAccounts;
    }

    public AccountDetails deleteAccountById(long accountId) {
        if (!accountDetailsRepository.existsById(accountId)) {
            throw new NoAccountFoundException(("No accound found with given account id"));
        }
        AccountDetails accountDetails = accountDetailsRepository.findById(accountId).get();
        accountDetailsRepository.deleteById(accountId);
        return accountDetails;
    }
}
