package com.skilldistillery.skiroutes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skiroutes.entities.Lift;
import com.skilldistillery.skiroutes.entities.Peak;
import com.skilldistillery.skiroutes.entities.Route;
import com.skilldistillery.skiroutes.repositories.LiftRepository;
import com.skilldistillery.skiroutes.repositories.PeakRepository;

@Service
public class LiftServiceImpl implements LiftService {
	
	@Autowired
	private LiftRepository liftRepo;
	
	@Autowired
	private PeakRepository peakRepo;

	@Override
	public List<Lift> getAllPeaks() {
		// TODO Auto-generated method stub
		return liftRepo.findAll();
	}

	@Override
	public Lift getLiftByID(int id) {
		// TODO Auto-generated method stub
		return liftRepo.queryById(id);
	}
	
	@Override
	public List<Lift> findByPeakId(int id) {
		// TODO Auto-generated method stub
		if(! liftRepo.existsById(id)) {
			return null;
		}
		List<Lift> lifts =liftRepo.findByPeak_Id(id); 
		return lifts;
	}

	@Override
	public List<Lift> findByLiftName(String name) {
		// TODO Auto-generated method stub
		name = "%"+name.toUpperCase()+"%";
		return liftRepo.findLiftByNameLike(name);
	}

	@Override
	public Lift addLift(int peakId ,Lift lift) {
		// TODO Auto-generated method stub
		Peak peak = peakRepo.queryById(peakId);
		if(peak!=null) {
			lift.setPeak(peak);
			liftRepo.saveAndFlush(lift);
			return lift;
		}
		return null;
	}

	@Override
	public Lift updateLift(int id, Lift lift) {
		// TODO Auto-generated method stub
		Lift existing = this.getLiftByID(id);
		if(existing !=null) {
			existing.setCarrierNumber(lift.getCarrierNumber());
			existing.setName(lift.getName());
			existing.setRunTime(lift.getRunTime());
			liftRepo.saveAndFlush(existing);
		}
		return existing;
	}

	@Override
	public boolean deleteLift(int liftId) {
		// TODO Auto-generated method stub
		Lift existing = this.getLiftByID(liftId);
		boolean deleted = false;
		if(existing !=null) {
			int peakId = existing.getPeak().getId();
			if(peakRepo.existsById(peakId)) {
				liftRepo.deleteById(liftId);
				deleted= true;
			}
		}
		return deleted;
	}

}
