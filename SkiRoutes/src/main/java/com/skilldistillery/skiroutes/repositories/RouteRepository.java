package com.skilldistillery.skiroutes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skiroutes.entities.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {

}
