package com.example.demo.repository;

import com.example.demo.model.Debt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DebtRepository extends JpaRepository <Debt, Long> {

    List<Debt> findByUserId(Long userId);

    @Transactional
    @Procedure(procedureName = "PAY_DEBT")
    void executarPagamento(@Param("P_ID_DIVIDA") Long debtId);

}


