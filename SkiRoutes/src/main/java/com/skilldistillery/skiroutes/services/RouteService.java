package com.skilldistillery.skiroutes.services;

import java.util.List;

import com.skilldistillery.skiroutes.entities.Route;

public interface RouteService {
	List<Route> getAllRoutes();
	Route getRouteById(int id);

}
