package com.skilldistillery.skiroutes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skiroutes.entities.SnowCondition;

public interface SnowConditionRepository extends JpaRepository<SnowCondition, Integer> {

	List<SnowCondition> findByTitleLike(String title);
}
