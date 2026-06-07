package com.paglins.repository;

import com.paglins.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository <Route, Long> {
    List<Route> findByUserId(Long userId);
}
