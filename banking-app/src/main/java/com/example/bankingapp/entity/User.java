package com.example.bankingapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", schema = "online_bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq",
            allocationSize = 1, initialValue = 1, schema = "online_bank")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phone")
    private long phone;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User(String firstName, String lastName, int age, String email, long phone, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }
}
