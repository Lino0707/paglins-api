package com.paglins.controller;


import com.paglins.model.Route;
import com.paglins.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paglins/routes")
@CrossOrigin(origins = "*")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService){
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity<Route> create(@RequestBody Route route) {
        return ResponseEntity.status(201).body(routeService.save(route));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Route>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(routeService.listarRotasPorUsuario(userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Route> update(@PathVariable Long id, @RequestBody Route route) {
        return ResponseEntity.ok(routeService.atualizar(id, route));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        routeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
