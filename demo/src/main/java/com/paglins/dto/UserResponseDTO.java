package com.paglins.dto;

import java.math.BigDecimal;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private BigDecimal balance;
    private BigDecimal nextBalance;

    public UserResponseDTO(Long id, String name, String lastName, String email, BigDecimal balance, BigDecimal nextBalance) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.balance = balance;
        this.nextBalance = nextBalance;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public BigDecimal getBalance() { return balance; }
    public BigDecimal getNextBalance() { return nextBalance; }
}