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
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phone")
    private long phone;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AccountDetails accountDetails;
}
