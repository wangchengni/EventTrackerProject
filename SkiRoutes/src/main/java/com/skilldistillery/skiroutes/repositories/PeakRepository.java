package com.skilldistillery.skiroutes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skiroutes.entities.Peak;

public interface PeakRepository extends JpaRepository<Peak, Integer> {

}
