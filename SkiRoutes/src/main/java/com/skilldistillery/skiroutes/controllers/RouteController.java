package com.skilldistillery.skiroutes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skiroutes.entities.Route;
import com.skilldistillery.skiroutes.services.RouteService;

@RestController
@RequestMapping("api")
public class RouteController {
	
	@Autowired
	private RouteService routeSev;
	
	@GetMapping("routes")
	public List<Route> index(){
		
		return routeSev.getAllRoutes();
	}
	@GetMapping("routes/{id}")
	public Route getRoutesById(@PathVariable int id){
		
		return routeSev.getRouteById(id);
	}
	@GetMapping("routes/search/{keyword}")
	public List<Route> findRouteByNameOrLevel(@PathVariable String keyword){
		
		return routeSev.findByKeywords(keyword);
	}
//	@GetMapping("routes")
//	public List<Route> index2(){
//		
//		return routeSev.getAllRoutes();
//	}
//	@GetMapping("routes")
//	public List<Route> index3(){
//		
//		return routeSev.getAllRoutes();
//	}
	@GetMapping("routes/condition/{snowcondition}")
	public List<Route> findBySnowCondition(@PathVariable String snowcondition){
		
		return routeSev.findBySnowCondition(snowcondition);
	}
	@GetMapping("routes/distance/{distance}")
	public List<Route> findbyDistanceGreaterThan(@PathVariable double distance){
		
		return routeSev.findByDistanceGreaterThan(distance);
	}
//	@GetMapping("routes")
//	public List<Route> index3(){
//		
//		return routeSev.getAllRoutes();
//	}
//	@GetMapping("routes")
//	public List<Route> index3(){
//		
//		return routeSev.getAllRoutes();
//	}

}
