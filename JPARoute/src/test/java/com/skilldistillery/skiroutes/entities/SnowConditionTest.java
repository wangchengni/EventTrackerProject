package com.skilldistillery.skiroutes.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SnowConditionTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private SnowCondition condition;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPARoute");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em=emf.createEntityManager();
		condition =em.find(SnowCondition.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		condition = null;
		
	}
	@Test
	void test() {
		assertNotNull(condition);
		assertEquals("Icy", condition.getTitle());
	}
	

}
