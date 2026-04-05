package com.example.demo.service;

import com.example.demo.model.Debt;
import com.example.demo.repository.DebtRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DebtServiceTest {

    @Mock
    private DebtRepository debtRepository;

    @InjectMocks
    private DebtService debtService;

    @Test
    void deveSalvarDivida() {
        Debt debt = new Debt();
        debt.setUserId(1L);

        when(debtRepository.save(debt)).thenReturn(debt);

        Debt resultado = debtService.salvar(debt);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getUserId());
        verify(debtRepository, times(1)).save(debt);
    }

    @Test
    void deveListarDividasPorUsuario() {
        Debt debt = new Debt();
        debt.setUserId(1L);

        when(debtRepository.findByUserId(1L)).thenReturn(List.of(debt));

        List<Debt> resultado = debtService.listarDividasPorUsuario(1L);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(debtRepository, times(1)).findByUserId(1L);
    }

    @Test
    void devePagarDivida() {
        debtService.pagar(1L);

        verify(debtRepository, times(1)).executarPagamento(1L);
    }

    @Test
    void deveDeletarDivida() {
        debtService.deletar(1L);

        verify(debtRepository, times(1)).deleteById(1L);
    }
}