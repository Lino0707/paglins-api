package com.example.demo.service;

import com.example.demo.model.Route;
import com.example.demo.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteService routeService;

    @Test
    void deveSalvarRota() {
        Route route = new Route();
        route.setUserId(1L);
        route.setTotalEarnings(new BigDecimal("150.00"));

        when(routeRepository.save(route)).thenReturn(route);

        Route resultado = routeService.save(route);

        assertNotNull(resultado);
        assertEquals(new BigDecimal("150.00"), resultado.getTotalEarnings());
        verify(routeRepository, times(1)).save(route);
    }

    @Test
    void deveListarRotasPorUsuario() {
        Route route = new Route();
        route.setUserId(1L);

        when(routeRepository.findByUserId(1L)).thenReturn(List.of(route));

        List<Route> resultado = routeService.listarRotasPorUsuario(1L);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(routeRepository, times(1)).findByUserId(1L);
    }

    @Test
    void deveAtualizarRota() {
        Route routeExistente = new Route();
        routeExistente.setRouteId(1L);
        routeExistente.setTotalEarnings(new BigDecimal("100.00"));

        Route routeAtualizada = new Route();
        routeAtualizada.setTotalEarnings(new BigDecimal("200.00"));
        routeAtualizada.setPackages(new BigDecimal("50.00"));
        routeAtualizada.setRouteDate(LocalDate.now());

        when(routeRepository.findById(1L)).thenReturn(Optional.of(routeExistente));
        when(routeRepository.save(any(Route.class))).thenReturn(routeExistente);

        Route resultado = routeService.atualizar(1L, routeAtualizada);

        assertNotNull(resultado);
        assertEquals(new BigDecimal("200.00"), resultado.getTotalEarnings());
        verify(routeRepository, times(1)).findById(1L);
        verify(routeRepository, times(1)).save(routeExistente);
    }

    @Test
    void deveLancarExcecaoQuandoRotaNaoEncontrada() {
        when(routeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> routeService.atualizar(99L, new Route()));
        verify(routeRepository, never()).save(any());
    }

    @Test
    void deveDeletarRota() {
        routeService.deletar(1L);

        verify(routeRepository, times(1)).deleteById(1L);
    }
}