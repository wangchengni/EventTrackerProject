package com.skilldistillery.skiroutes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skiroutes.entities.Lift;

public interface LiftRepository extends JpaRepository<Lift, Integer> {

	List<Lift> findLiftByNameLike(String name);
	List<Lift> findByPeak_Id(int peakId);
	Lift queryById(int id);
}
