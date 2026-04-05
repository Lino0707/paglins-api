package com.example.demo.service;

import com.example.demo.model.Route;
import com.example.demo.model.User;
import com.example.demo.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> listarRotasPorUsuario(Long userId){
        return routeRepository.findByUserId(userId);
    }

    public Route save(Route route) {
        return  routeRepository.save(route);
    }

    public Route atualizar(Long id, Route routeAtualizada) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
        route.setTotalEarnings(routeAtualizada.getTotalEarnings());
        route.setPackages(routeAtualizada.getPackages());
        route.setRouteDate(routeAtualizada.getRouteDate());
        return routeRepository.save(route);
    }

    public void deletar(Long id) {
        routeRepository.deleteById(id);
    }

}
