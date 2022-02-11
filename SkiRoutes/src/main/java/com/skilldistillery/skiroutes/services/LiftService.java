package com.skilldistillery.skiroutes.services;

import java.util.List;

import com.skilldistillery.skiroutes.entities.Lift;

public interface LiftService {

	//------Search
		List<Lift> getAllPeaks();
		Lift getLiftByID(int id);
		
		//-------Repo
		List<Lift> findByLiftName(String name);
		
		//-------CRUD
		Lift addLift(int peakId, Lift lift);
		Lift updateLift(int id, Lift lift);
		boolean deleteLift(int liftId);
}
