package com.example.bankingapp.service;

import com.example.bankingapp.exceptions.NoUserExistsException;
import com.example.bankingapp.exceptions.UserExistsException;
import com.example.bankingapp.exceptions.UserNotFoundException;
import com.example.bankingapp.utils.RequestPOJO;
import com.example.bankingapp.dao.AccountDetailsRepository;
import com.example.bankingapp.dao.UserRepository;
import com.example.bankingapp.entity.AccountDetails;
import com.example.bankingapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankingAppService {
    private UserRepository userRepository;
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    public BankingAppService(UserRepository userRepository, AccountDetailsRepository accountDetailsRepository) {
        this.userRepository = userRepository;
        this.accountDetailsRepository = accountDetailsRepository;
    }

    public AccountDetails createOrUpdate(RequestPOJO requestPOJO) {
        if (userRepository.existsByEmail(requestPOJO.getEmail())) {
            throw new UserExistsException("User already exists with the email ID");
        }
        AccountDetails accountDetails = requestPOJO.toAccountDetails();
        User user = userRepository.save(accountDetails.getUser());
        accountDetails.setUser(user);
        accountDetailsRepository.save(accountDetails);
        return accountDetails;
    }

    public AccountDetails findUser(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElse(null);
        if (user == null) {
            throw new UserNotFoundException("No user present with given user id") ;
        }
        AccountDetails accountDetails = accountDetailsRepository.findByUserId(userId);
        accountDetails.setUser(user);
        return accountDetails;
    }

    public List<AccountDetails> findAll() {
        List<AccountDetails> accountDetails = accountDetailsRepository.findAll();
        if (accountDetails.isEmpty()) {
            throw new NoUserExistsException("No user present in the database");
        }
        return accountDetails;
    }

    public List<AccountDetails> deleteAll() {
        List<AccountDetails> accountDetails = accountDetailsRepository.findAll();
        if (accountDetails.isEmpty()) {
            throw new NoUserExistsException("No user present in the database");
        }
        accountDetailsRepository.deleteAll();
        userRepository.deleteAll();
        return accountDetails;
    }

    public AccountDetails deleteById(long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("No user present with given user id") ;
        }
        AccountDetails accountDetails = accountDetailsRepository.findByUserId(userId);
        accountDetails.setUser(user);
        accountDetailsRepository.deleteByUserId(userId);
        userRepository.deleteById(userId);
        return accountDetails;
    }
}
