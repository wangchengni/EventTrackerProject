package com.skilldistillery.skiroutes.services;

import java.util.List;

import com.skilldistillery.skiroutes.entities.Peak;

public interface PeakService {

	//------Search
	List<Peak> getAllPeaks();
	Peak getPeakByID(int id);
	
	//-------Repo
	List<Peak> findByPeakName(String name);
	
	//-------CRUD
	Peak addPeak(Peak peak);
	Peak updatePeak(int id, Peak peak);
	boolean deletePeak(int peakId);
}
