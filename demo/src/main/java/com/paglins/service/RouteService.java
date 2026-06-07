package com.paglins.service;

import com.paglins.model.Route;
import com.paglins.repository.RouteRepository;
import com.paglins.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> listarRotasPorUsuario(Long userId){
        return routeRepository.findByUserId(userId);
    }

    private static final List<String> STATUS_VALIDOS = List.of("OPEN", "CLOSE", "PAID");

    public Route save(Route route) {
        if (route.getStatus() != null && !STATUS_VALIDOS.contains(route.getStatus())) {
            throw new RuntimeException("Status inválido: " + route.getStatus() + ". Use OPEN, CLOSE ou PAID.");
        }
        if (route.getStatus() == null) {
            route.setStatus("OPEN");
        }
        return routeRepository.save(route);
    }

    public Route atualizar(Long id, Route routeAtualizada) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rota não encontrada com ID: " + id));
        route.setTotalEarnings(routeAtualizada.getTotalEarnings());
        route.setPackages(routeAtualizada.getPackages());
        route.setRouteDate(routeAtualizada.getRouteDate());
        return routeRepository.save(route);
    }

    public BigDecimal calcularProximoSaldo(Long userId) {
        return routeRepository.findByUserId(userId)
                .stream()
                .filter(r -> "OPEN".equals(r.getStatus()))
                .map(Route::getTotalEarnings)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void deletar(Long id) {
        routeRepository.deleteById(id);
    }

}
