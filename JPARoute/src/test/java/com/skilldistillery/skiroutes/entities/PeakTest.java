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

class PeakTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Peak peak;
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
		peak =em.find(Peak.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		peak = null;
		
	}

	@Test
	void test() {
		assertNotNull(peak);
		assertEquals("12998 ft", peak.getElevation());
	
	}
	@Test
	void test_Routes() {
		assertNotNull(peak.getRoutes());
		assertTrue(peak.getRoutes().size()>0);
		
	}
	@Test
	void test_Lifts() {
		assertNotNull(peak.getLifts());
		assertTrue(peak.getLifts().size()>0);
		
	}

}
