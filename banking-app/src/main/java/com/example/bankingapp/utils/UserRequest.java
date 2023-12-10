package com.example.bankingapp.utils;

import com.example.bankingapp.entity.Gender;
import com.example.bankingapp.entity.User;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "First name should not be empty")
    @Size(min = 2, message = "First name size should atleast be 2")
    private String firstName;
    @NotBlank(message = "Last name should not be empty")
    @Size(min = 1, message = "Last name size should at least be 1")
    private String lastName;
    @NotNull(message = "Age should not be empty")
    private Integer age;
    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Phone number should not be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number should be 10 digits")
    private String phone;
    @NotNull(message = "Gender is necessary")
    private Gender gender;

    public User toUser() {
        User user = new User(getFirstName(),
                getLastName(),
                getAge(),
                getEmail(),
                Long.parseLong(getPhone()),
                getGender());
        return user;
    }
}
