package com.example.bankingapp.utils;

import com.example.bankingapp.entity.Gender;
import com.example.bankingapp.entity.User;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateRequest {
    @Size(min = 2, message = "First name size should atleast be 2")
    private String firstName;
    @Size(min = 1, message = "Last name size should at least be 1")
    private String lastName;
    private int age;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n", message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "Phone number should be 10 digits")
    private String phone;
    private Gender gender;

    public User toUser(User user) {
        if (firstName != null) {
            if (!firstName.isEmpty()) {
                if (!firstName.isBlank()) {
                    user.setFirstName(firstName);
                }
            }
        }
        if (lastName != null) {
            if (!lastName.isEmpty()) {
                if (!lastName.isBlank()) {
                    user.setLastName(lastName);
                }
            }
        }
        if (age > 0) {
            user.setAge(age);
        }
        if (email != null) {
            if (!email.isEmpty()) {
                if (!email.isBlank()) {
                    user.setEmail(email);
                }
            }
        }

        if (phone != null) {
            if (!phone.isEmpty()) {
                if (!phone.isBlank()) {
                    user.setPhone(Long.parseLong(phone));
                }
            }
        }
        if (gender != null) {
            user.setGender(gender);
        }
        return user;
    }
}
