package com.example.demo.controller;

import com.example.demo.model.Debt;
import com.example.demo.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paglins/debts")
public class DebtController {

    @Autowired
    private DebtService debtService;

    @GetMapping("/user/{userId}")
    public List<Debt> getByUser(@PathVariable Long userId) {
        return debtService.listarDividasPorUsuario(userId);
    }

    @PostMapping
    public Debt create(@RequestBody Debt debt) {
        return debtService.salvar(debt);
    }

    @PatchMapping("/{id}/pay")
    public void pay(@PathVariable Long id) {
        debtService.pagar(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        debtService.deletar(id);
    }

}

