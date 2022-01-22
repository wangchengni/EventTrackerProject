package com.skilldistillery.skiroutes.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skiroutes.entities.Route;
import com.skilldistillery.skiroutes.services.RouteService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*","http://localhost:4202"})
public class RouteController {
	
	@Autowired
	private RouteService routeSev;
	
	@GetMapping("routes")
	public List<Route> index(){
		
		return routeSev.getAllRoutes();
	}
	@GetMapping("routes/{id}")
	public Route getRoutesById(@PathVariable int id, HttpServletResponse res){
		Route route = routeSev.getRouteById(id);
		if(route == null) {
			res.setStatus(404);
		}
		return route;
	}
	@GetMapping("routes/search/{keyword}")
	public List<Route> findRouteByNameOrLevel(@PathVariable String keyword){
		
		return routeSev.findByKeywords(keyword);
	}
	@GetMapping("routes/lift/{id}")
	public List<Route> findByLiftId(@PathVariable int id,HttpServletResponse res ){
		List<Route> routes = routeSev.findByLiftId(id);
		if(routes == null) {
			res.setStatus(404);
		}
		
		return routes;
	}
	@GetMapping("routes/liftName/{name}")
	public List<Route> findByliftName(@PathVariable String name){
		
		return routeSev.findByLiftName(name);
	}
	@GetMapping("routes/condition/{snowcondition}")
	public List<Route> findBySnowCondition(@PathVariable String snowcondition){
		
		return routeSev.findBySnowCondition(snowcondition);
	}
	@GetMapping("routes/distance/{distance}")
	public List<Route> findbyDistanceGreaterThan(@PathVariable double distance){
		
		return routeSev.findByDistanceGreaterThan(distance);
	}
	@GetMapping("routes/orderBy/{level}")
	public List<Route> findByOrderLevel(@PathVariable String level){
		
		return routeSev.OrderByLevel(level);
	}
//	@GetMapping("routes/orderAll/{level}")
//	public List<Route> index3(@PathVariable String level){
//		
//		return routeSev.OrderByLevel(level);
//	}
	@PostMapping("lifts/{id}/routes")
	public Route showCreated(
				@PathVariable int id, 
				@RequestBody Route route, 
				HttpServletRequest req,
				HttpServletResponse res){
		route = routeSev.addRoute(id, route);
		if(route == null) {
			res.setStatus(404);
		}else {
			res.setStatus(201);
			StringBuffer url =req.getRequestURL();
			url.append("/").append(route.getId());
			res.setHeader("Location", url.toString());
		}
		return route;
	}
	@PutMapping("routes/{id}")
	public Route replaceRoute(
			@PathVariable int id, 
			@RequestBody Route route,
			HttpServletResponse res
			){
		try {
			route = routeSev.updateRoute(id, route);
			if(route == null) {
				res.setStatus(404);
			}
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			route = null;
		}
		
		return route;
	}
	@DeleteMapping("routes/{routeId}")
	public void deletRoute(
			@PathVariable int routeId,
			HttpServletResponse res) {
		try {
			if(routeSev.delete(routeId)) {
				res.setStatus(HttpStatus.NO_CONTENT.value());
			}else {
				res.setStatus(404);
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			}
	}
	

}
