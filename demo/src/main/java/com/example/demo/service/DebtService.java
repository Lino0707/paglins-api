package com.example.demo.service;

import com.example.demo.model.Debt;
import com.example.demo.repository.DebtRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DebtService {

    @Autowired
    private DebtRepository debtRepository;

    public List<Debt> listarDividasPorUsuario(Long userId) {
        List<Debt> debts = debtRepository.findByUserId(userId);
        LocalDate hoje = LocalDate.now();

        for (Debt debt : debts) {
            if (debt.getDueDate().isBefore(hoje) && !"PAID".equals(debt.getStatus())) {
                debt.setStatus("OVERDUE");
                debtRepository.save(debt);
            }
        }
        return debts;
    }

    public void pagar(Long id) {
        debtRepository.executarPagamento(id);
    }

    public Debt salvar(Debt debt) {
        return debtRepository.save(debt);
    }

    public void deletar(Long id) {
        debtRepository.deleteById(id);
    }

    public List<Debt> listarTodasAsDividas() {
        return debtRepository.findAll();
    }
}