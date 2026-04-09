package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "DEBTS")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEBT_ID")
    @JsonProperty("id") // O front espera "id"
    private Long debtId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DUE_DATE")
    @JsonFormat(pattern = "dd/MM/yyyy") // Formata a data para o front
    private LocalDate dueDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PAYMENT_DATE")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;
}
