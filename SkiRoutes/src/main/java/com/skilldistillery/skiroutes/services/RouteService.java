package com.skilldistillery.skiroutes.services;

import java.util.List;

import com.skilldistillery.skiroutes.entities.Route;

public interface RouteService {
	
	/*
	 * ------------------------
	 * Search
	 * ------------------------
	 */
	List<Route> getAllRoutes();
	Route getRouteById(int id);
	
	/*
	 * ------------------------
	 * Repository
	 * ------------------------
	 */
	List<Route> findByKeywords(String keyword);
	List<Route> findByLiftId (int id);
	List<Route> findByLiftName(String name);
	List<Route> findBySnowCondition(String snowCondition);
	List<Route> findByDistanceGreaterThan(double distance);
	List<Route> OrderByLevel(String level);
//	List<Route> findAllOrderByLevel(String level);
	
	
	/*
	 * -------------------------
	 * Create&Update%delete
	 * -------------------------
	 */
	Route addRoute (int id, Route route);
	Route updateRoute(int id, Route route);
	boolean delete(int routeId );

}
