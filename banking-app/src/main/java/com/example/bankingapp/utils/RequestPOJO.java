package com.example.bankingapp.utils;

import com.example.bankingapp.entity.AccountDetails;
import com.example.bankingapp.entity.AccountType;
import com.example.bankingapp.entity.Gender;
import com.example.bankingapp.entity.User;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestPOJO {
    @NotBlank(message = "First name should not be empty")
    @Size(min = 2, message = "First name size should atleast be 2")
    private String firstName;
    @NotBlank(message = "Last name should not be empty")
    @Size(min = 2, message = "Last name size should at least be 2")
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
    @NotNull(message = "Account type is necessary")
    private AccountType accountType;

    public AccountDetails toAccountDetails() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountType(accountType);
        if (getAccountType().equals(AccountType.CURRENT)) {
            accountDetails.setBalance(500.00);
        }
        accountDetails.setUser(new User(getFirstName(),
                getLastName(),
                getAge(),
                getEmail(),
                Long.parseLong(getPhone()),
                getGender()));
        return accountDetails;
    }
}
