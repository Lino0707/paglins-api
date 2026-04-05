package com.example.demo.service;

import com.example.demo.model.Debt;
import com.example.demo.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DebtService {

    @Autowired
    private DebtRepository debtRepository;

    public List<Debt> listarDividasPorUsuario(Long userId) {
        return debtRepository.findByUserId(userId);
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
}