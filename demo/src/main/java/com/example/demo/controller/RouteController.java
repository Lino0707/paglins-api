package com.example.demo.controller;


import com.example.demo.model.Route;
import com.example.demo.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paglins/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping
    public Route create(@RequestBody Route route) {
        return routeService.save(route);
    }

    @GetMapping("/user/{userId}")
    public List<Route> getByUser(@PathVariable Long userId) {
        return routeService.listarRotasPorUsuario(userId);
    }

    @PatchMapping("/{id}")
    public Route update(@PathVariable Long id, @RequestBody Route route) {
        return routeService.atualizar(id, route);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        routeService.deletar(id);
    }
}
