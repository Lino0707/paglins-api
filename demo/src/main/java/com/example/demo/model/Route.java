package com.example.demo.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ROUTES")
@Data

public class Route {

    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROUTE_ID")
    private Long routeId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "ROUTE_DATE")
    private LocalDate routeDate;

    @Column(name = "CREATE_AT")
    private LocalDate createAT;

    @Column(name = "TOTAL_EARNINGS")
    private BigDecimal totalEarnings;

    @Column(name = "PACKAGES_EARNINGS")
    private BigDecimal packages;

    @Column(name = "STATUS")
    private String status;

}

