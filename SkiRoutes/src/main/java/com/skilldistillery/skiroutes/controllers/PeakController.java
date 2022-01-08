package com.skilldistillery.skiroutes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skiroutes.services.PeakService;

@RestController
@RequestMapping("api")
public class PeakController {
	@Autowired
	private PeakService peakSev;

}
