package com.skilldistillery.skiroutes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skiroutes.entities.Peak;
import com.skilldistillery.skiroutes.repositories.PeakRepository;

@Service
public class PeakServiceImpl implements PeakService {

	@Autowired
	private PeakRepository peakRepo;
	
	
	@Override
	public List<Peak> getAllPeaks() {
		// TODO Auto-generated method stub
		return peakRepo.findAll();
	}

	@Override
	public Peak getPeakByID(int id) {
		// TODO Auto-generated method stub
		Optional<Peak> op = peakRepo.findById(id);
		Peak peak = null;
		if(op.isPresent()) {
			peak = op.get();
		}
		return peak;
	}

	@Override
	public List<Peak> findByPeakName(String name) {
		// TODO Auto-generated method stub
		name = "%"+name.toUpperCase()+"%";
		return peakRepo.findPeakByName_Like(name);
	}

	@Override
	public Peak addPeak(Peak peak) {
		// TODO Auto-generated method stub
		peakRepo.saveAndFlush(peak);
		return peak;
	}

	@Override
	public Peak updatePeak(int id, Peak peak) {
		// TODO Auto-generated method stub
		Optional<Peak> op = peakRepo.findById(id);
		Peak managed = null;
		if(op.isPresent()) {
			managed = op.get();
			managed.setElevation(peak.getElevation());
			managed.setName(peak.getName());
			peakRepo.saveAndFlush(managed);
			
		}
		return managed;
	}

	@Override
	public boolean deletePeak(int peakId) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		Optional<Peak> op = peakRepo.findById(peakId);
		if(op.isPresent()) {
			Peak peak = op.get();
			peakRepo.delete(peak);
			deleted = true;
		}
		return deleted;
		
	}

}
