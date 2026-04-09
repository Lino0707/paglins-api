package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("id")
    private Long userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BALANCE")
    private Double balance;

    @Column(name = "NEXT_BALANCE")
    private Double nextBalance;

    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;

}