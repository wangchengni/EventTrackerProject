package com.skilldistillery.skiroutes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skiroutes.entities.Route;
import com.skilldistillery.skiroutes.repositories.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	RouteRepository routeRepo;
	@Override
	public List<Route> getAllRoutes() {
		// TODO Auto-generated method stub
		return routeRepo.findAll();
	}

	@Override
	public Route getRouteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
