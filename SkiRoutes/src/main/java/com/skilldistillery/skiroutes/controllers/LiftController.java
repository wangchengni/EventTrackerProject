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

import com.skilldistillery.skiroutes.entities.Lift;
import com.skilldistillery.skiroutes.entities.Route;
import com.skilldistillery.skiroutes.services.LiftService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*","http://localhost:4202"})
public class LiftController {
	@Autowired
	private LiftService liftSev;

	@GetMapping("lifts")
	public List<Lift> index() {
		return liftSev.getAllPeaks();
	}

	@GetMapping("lifts/{liftId}")
	public Lift getLiftById(@PathVariable int liftId, HttpServletResponse res) {
		Lift lift = liftSev.getLiftByID(liftId);
		if (lift == null) {
			res.setStatus(404);
		}
		return lift;
	}
	
	@GetMapping("lifts/peak/{liftId}")
	public List<Lift> findByLiftId(@PathVariable int liftId,HttpServletResponse res ){
		List<Lift> lifts = liftSev.findByPeakId(liftId);
		if(lifts == null) {
			res.setStatus(404);
		}
		
		return lifts;
	}

	@GetMapping("lifts/search/{name}")
	public List<Lift> getLiftsByName(@PathVariable String name) {
		return liftSev.findByLiftName(name);
	}

	@PostMapping("lifts/{peakId}/lift")
	public Lift addLift(@PathVariable int peakId, @RequestBody Lift lift, HttpServletRequest req, HttpServletResponse res) {
		lift = liftSev.addLift(peakId, lift);
		if (lift == null) {
			res.setStatus(404);
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(lift.getId());
			res.setHeader("Location", url.toString());
		}
		return lift;
	}

	@PutMapping("lifts/{id}")
	public Lift updateLift(@PathVariable int id, @RequestBody Lift lift, HttpServletRequest req,
			HttpServletResponse res) {
		try {
			lift = liftSev.updateLift(id, lift);
			if (lift == null) {
				res.setStatus(404);
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			lift = null;
		}
		return lift;
	}

	@DeleteMapping("lifts/{id}")
	public void deleteLift(@PathVariable int id, HttpServletResponse res) {
		try {
			if (liftSev.deleteLift(id)) {
				res.setStatus(HttpStatus.NO_CONTENT.value());
			} else {
				res.setStatus(404);
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
}
