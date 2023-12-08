package com.example.bankingapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "account_details", schema = "online_bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_seq",
            allocationSize = 1, initialValue = 1000000000, schema = "online_bank")
    private long accountNum;
    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDate date;
    @Column(name = "balance")
    @ColumnDefault("0.0")
    private double balance;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
