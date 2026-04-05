package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "USERS")
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "BALANCE")
    private Double balance;

    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;

    @Column(name = "NEXT_BALANCE")
    private Double nextBalance;

    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
    }
}
