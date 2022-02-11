package com.skilldistillery.skiroutes.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skiroutes.entities.Peak;
import com.skilldistillery.skiroutes.services.PeakService;

@RestController
@RequestMapping("api")
public class PeakController {
	@Autowired
	private PeakService peakSev;

	@GetMapping("peaks")
	public List<Peak> index() {
		return peakSev.getAllPeaks();
	}

	@GetMapping("peaks/{id}")
	public Peak getPeakById(@PathVariable int id, HttpServletResponse res) {
		Peak peak = peakSev.getPeakByID(id);
		if (peak == null) {
			res.setStatus(404);
		}
		return peak;
	}

	@GetMapping("peaks/search/{name}")
	public List<Peak> findByPeakName(@PathVariable String name) {
		return peakSev.findByPeakName(name);
	}

	@PostMapping("peaks/peak")
	public Peak showCreated(@RequestBody Peak peak, HttpServletRequest req, HttpServletResponse res) {
		peak = peakSev.addPeak(peak);
		if (peak == null) {
			res.setStatus(404);
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(peak.getId());
			res.setHeader("Location", url.toString());
		}
		return peak;
	}

	@PutMapping("peaks/{id}")
	public Peak updatePeak(@PathVariable int id, @RequestBody Peak peak, HttpServletRequest req,
			HttpServletResponse res) {
		try {
			peak = peakSev.updatePeak(id, peak);
			if (peak == null) {
				res.setStatus(404);
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			peak = null;
		}
		return peak;
	}

	@DeleteMapping("peaks/{id}")
	public void deletePeak(@PathVariable int id, HttpServletResponse res) {
		try {
			if (peakSev.deletePeak(id)) {
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
