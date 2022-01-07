package com.skilldistillery.skiroutes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
