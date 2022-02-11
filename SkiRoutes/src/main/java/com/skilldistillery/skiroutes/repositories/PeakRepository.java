package com.skilldistillery.skiroutes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skiroutes.entities.Peak;

public interface PeakRepository extends JpaRepository<Peak, Integer> {
	List<Peak> findPeakByName_Like(String name);
	Peak queryById(int id);
}
