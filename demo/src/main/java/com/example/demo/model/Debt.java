package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "DEBTS")
@Data

public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEBT_ID")
    private Long debtId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PAYMENT_DATE")
    private LocalDate paymentDate;

}
