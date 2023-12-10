package com.example.bankingapp.service;

import com.example.bankingapp.dao.AccountDetailsRepository;
import com.example.bankingapp.dao.UserRepository;
import com.example.bankingapp.entity.User;
import com.example.bankingapp.exceptions.NoUserExistsException;
import com.example.bankingapp.exceptions.UserExistsException;
import com.example.bankingapp.exceptions.UserNotFoundException;
import com.example.bankingapp.utils.UserRequest;
import com.example.bankingapp.utils.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    public UserService(UserRepository userRepository, AccountDetailsRepository accountDetailsRepository) {
        this.userRepository = userRepository;
        this.accountDetailsRepository = accountDetailsRepository;
    }

    public User createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new UserExistsException("User already exists with the given email");
        }
        return userRepository.saveAndFlush(userRequest.toUser());
    }

    public User getUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("No user found with given user id");
        }
        return userRepository.findById(id).get();
    }

    public User updateUserById(long id, UserUpdateRequest userUpdateRequest) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("No user found with given user id");
        }
        User user = userRepository.findById(id).get();
        user = userUpdateRequest.toUser(user);
        return userRepository.saveAndFlush(user);
    }

    public User deleteById(long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("No user found with given user id");
        }
        User user = userRepository.findById(id).get();
        accountDetailsRepository.deleteByUserId(id);
        userRepository.deleteById(id);
        return user;
    }

    public List<User> deleteAllUsers() {
        if (userRepository.findAll().isEmpty()) {
            throw new NoUserExistsException("No user record exists");
        }
        List<User> users = userRepository.findAll();
        accountDetailsRepository.deleteAll();;
        userRepository.deleteAll();
        return users;
    }

    public List<User> readAllUsers() {
        if (userRepository.findAll().isEmpty()) {
            throw new NoUserExistsException("No user record exists");
        }
        return userRepository.findAll();
    }
}
