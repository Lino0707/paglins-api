package com.paglins.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DebtRequestDTO {

    private Long userId;
    private String description;
    private BigDecimal amount;
    private LocalDate firstDueDate;
    private Integer totalInstallments;

    // Getters e Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getFirstDueDate() { return firstDueDate; }
    public void setFirstDueDate(LocalDate firstDueDate) { this.firstDueDate = firstDueDate; }

    public Integer getTotalInstallments() { return totalInstallments; }
    public void setTotalInstallments(Integer totalInstallments) { this.totalInstallments = totalInstallments; }

}