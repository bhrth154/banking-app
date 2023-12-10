package com.example.bankingapp.controller;

import com.example.bankingapp.entity.User;
import com.example.bankingapp.service.UserService;
import com.example.bankingapp.utils.UserRequest;
import com.example.bankingapp.utils.UserUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class BankUserApi {
    private UserService userService;

    @Autowired
    public BankUserApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/read")
    public List<User> readAllUsers() {
        return userService.readAllUsers();
    }

    @GetMapping("/read/{id}")
    public User readUser(@PathVariable("id") long id) {
        return userService.getUser(id);
    }

    @PutMapping("/update/{id}")
    public User updateUserById(@PathVariable("id") long id, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUserById(id, userUpdateRequest);
    }

    @DeleteMapping("/delete")
    public List<User> deleteAllUser() {
        return userService.deleteAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUserById(@PathVariable("id") long id) {
        return userService.deleteById(id);
    }
}
