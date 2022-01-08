package com.skilldistillery.skiroutes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skiroutes.entities.Lift;

public interface LiftRepository extends JpaRepository<Lift, Integer> {

	List<Lift> findByNameLike(String name);
}
