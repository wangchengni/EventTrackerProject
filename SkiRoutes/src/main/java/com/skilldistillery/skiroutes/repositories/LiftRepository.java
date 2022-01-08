package com.skilldistillery.skiroutes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skiroutes.entities.Lift;

public interface LiftRepository extends JpaRepository<Lift, Integer> {

}
