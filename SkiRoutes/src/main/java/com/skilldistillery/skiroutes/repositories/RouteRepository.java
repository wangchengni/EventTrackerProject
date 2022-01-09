package com.skilldistillery.skiroutes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skiroutes.entities.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {

	List<Route> findByNameLikeOrLevelLike(String name, String level);
	List<Route> findByLift_id (int id);
	List<Route> findByLift_NameLike(String name);
	List<Route> findBySnowCondition(String snowCondition);
	List<Route> findByDistanceGreaterThan(double distance);
	List<Route> findByLevelOrderByLevel(String level);
//	List<Route> OrderByLevel(String level);
	
}
