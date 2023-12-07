package com.example.bankingapp.service;

import com.example.bankingapp.dao.AccountDetailsRepository;
import com.example.bankingapp.dao.UserRepository;
import com.example.bankingapp.entity.AccountDetails;
import com.example.bankingapp.entity.AccountType;
import com.example.bankingapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankingAppService {
    private UserRepository userRepository;
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    public BankingAppService(UserRepository userRepository, AccountDetailsRepository accountDetailsRepository) {
        this.userRepository = userRepository;
        this.accountDetailsRepository = accountDetailsRepository;
    }

    public AccountDetails createOrUpdate(User user) {
        userRepository.save(user);
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setUser(user);
        accountDetails.setAccountType(user.getAccountType());
        accountDetails.setBalance(user.getAccountType()== AccountType.CURRENT ? 500.0 : 0.0);
        accountDetails = accountDetailsRepository.save(accountDetails);
        return accountDetails;
    }

    public User findUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    public List<AccountDetails> deleteAll() {
        List<AccountDetails> accountDetails = accountDetailsRepository.findAll();
        accountDetailsRepository.deleteAll();
        return accountDetails;
    }

    public List<AccountDetails> findAll() {
        return accountDetailsRepository.findAll();
    }
}
