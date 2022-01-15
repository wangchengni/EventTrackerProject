package com.skilldistillery.skiroutes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skiroutes.entities.Lift;
import com.skilldistillery.skiroutes.entities.Route;
import com.skilldistillery.skiroutes.repositories.LiftRepository;
import com.skilldistillery.skiroutes.repositories.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteRepository routeRepo;
	@Autowired
	private LiftRepository liftRepo;
	
	/*
	 * ------------------------
	 * Search
	 * ------------------------
	 */
	@Override
	public List<Route> getAllRoutes() {
		// TODO Auto-generated method stub
		return routeRepo.findAll();
	}

	@Override
	public Route getRouteById(int id) {
		// TODO Auto-generated method stub
		Optional<Route> op = routeRepo.findById(id);
		Route route = null;
		if(op.isPresent()) {
			route = op.get();
		}
		return route;
	}

	/*
	 * ------------------------
	 * Repository
	 * ------------------------
	 */
	@Override
	public List<Route> findByKeywords(String keyword) {
		keyword ="%" +keyword +"%";
		return routeRepo.findByNameLikeOrLevelLike(keyword, keyword);
	}

	@Override
	public List<Route> findByLiftId(int id) {
		// TODO Auto-generated method stub
		if(! liftRepo.existsById(id)) {
			return null;
		}
		List<Route> routes =routeRepo.findByLift_id(id); 
		return routes;
	}

	@Override
	public List<Route> findByLiftName(String name) {
		if(liftRepo.findByNameLike(name).size()<=0) {
			return null;
		}
		List<Route> routes =routeRepo.findByLift_NameLike(name) ;
		
		return routes;
	}

	@Override
	public List<Route> findBySnowCondition(String snowCondition) {
		// TODO Auto-generated method stub
		
		List<Route> routes =routeRepo.findBySnowCondition(snowCondition) ;
		return routes;
	}

	@Override
	public List<Route> findByDistanceGreaterThan(double distance) {
		return routeRepo.findByDistanceGreaterThan(distance);
	}

	@Override
	public List<Route> OrderByLevel(String level) {
		return routeRepo.findByLevelOrderByLevel(level);
	}
//	@Override
//	public List<Route> findAllOrderByLevel(String level) {
//		return routeRepo.OrderByLevel(level);
//	}

	/*
	 * ---------------------------------
	 * Create&Update&Delete
	 * ---------------------------------
	 */
	@Override
	public Route addRoute(int id, Route route) {
		// TODO Auto-generated method stub
		Optional<Lift> op = liftRepo.findById(id);
		if(op.isPresent() && route.getName() !=null && route.getDistance() >0) {
			Lift lift = op.get();
			route.setLift(lift);
			route.setPeak(lift.getPeak());
			routeRepo.saveAndFlush(route);
			return route;
		}
		return null;
	}

	@Override
	public Route updateRoute(int id, Route route) {
		// TODO Auto-generated method stub
		Optional <Route> op = routeRepo.findById(id);
		Route managed = null;
		if(op.isPresent()) {
			managed = op.get();
			managed.setName(route.getName());
			managed.setDistance(route.getDistance());
			managed.setLevel(route.getLevel());
			managed.setSnowCondition(route.getSnowCondition());
//			managed.setLift(route.getLift());
//			managed.setPeak(route.getPeak());
			routeRepo.saveAndFlush(managed);
		}
		return managed;
	}

	@Override
	public boolean delete(int routeId) {
		boolean deleted = false;
		Optional<Route> op = routeRepo.findById(routeId);
		if(op.isPresent()) {
			Route route = op.get();
			int liftId = route.getLift().getId();
			if(liftRepo.existsById(liftId)) {
				routeRepo.deleteById(routeId);
				deleted = true;
			}
		}
		return deleted;
	}

}
