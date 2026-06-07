package com.paglins.service;

import com.paglins.dto.DebtRequestDTO;
import com.paglins.model.Debt;
import com.paglins.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class DebtService {

    @Autowired
    private final DebtRepository debtRepository;

    public DebtService(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }


    public List<Debt> listarDividasPorUsuario(Long userId) {
        List<Debt> debts = debtRepository.findByUserId(userId);
        return debts;
    }

    public void pagar(Long id) {
        debtRepository.executarPagamento(id);
    }

    public List<Debt> salvar(DebtRequestDTO dto) {
        List<Debt> parcelas = new ArrayList<>();

        BigDecimal valorParcela = dto.getAmount()
                .divide(BigDecimal.valueOf(dto.getTotalInstallments()), 2, RoundingMode.HALF_UP);

        for (int i = 1; i <= dto.getTotalInstallments(); i++) {
            Debt debt = new Debt();
            debt.setUserId(dto.getUserId());
            debt.setDescription(dto.getDescription());
            debt.setAmount(valorParcela);
            debt.setDueDate(dto.getFirstDueDate().plusMonths(i - 1));
            debt.setStatus("PENDING");
            debt.setType(dto.getTotalInstallments() > 1 ? "INSTALLMENT" : "SINGLE");
            debt.setInstallmentNumber(i);
            debt.setTotalInstallments(dto.getTotalInstallments());
            parcelas.add(debtRepository.save(debt));
        }

        return parcelas;
    }

    public void deletar(Long id) {
        debtRepository.deleteById(id);
    }

    public List<Debt> listarTodasAsDividas() {
        return debtRepository.findAll();
    }
}