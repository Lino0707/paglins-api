package com.paglins.controller;

import com.paglins.dto.DebtRequestDTO;
import com.paglins.model.Debt;
import com.paglins.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paglins/debts")
@CrossOrigin(origins = "*")
public class DebtController {

    private final DebtService debtService;

    public DebtController(DebtService debtService){
        this.debtService = debtService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Debt>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(debtService.listarDividasPorUsuario(userId));
    }

    @PostMapping
    public ResponseEntity<List<Debt>> create(@RequestBody DebtRequestDTO dto) {
        return ResponseEntity.status(201).body(debtService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Debt>> getAll() {
        return ResponseEntity.ok(debtService.listarTodasAsDividas());
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<Void> pay(@PathVariable Long id) {
        debtService.pagar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        debtService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}