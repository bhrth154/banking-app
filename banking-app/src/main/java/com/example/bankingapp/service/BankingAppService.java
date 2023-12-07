package com.example.bankingapp.service;

import com.example.bankingapp.dao.AccountDetailsRepository;
import com.example.bankingapp.dao.UserRepository;
import com.example.bankingapp.entity.AccountDetails;
import com.example.bankingapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User createOrUpdate(User user) {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setUser(user);
        accountDetails = accountDetailsRepository.save(accountDetails);
        return userRepository.save(user);
    }

    public User findUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }
}
