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

class LiftTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Lift lift;
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
		lift =em.find(Lift.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		lift = null;
		
	}

	@Test
	void test() {
		assertNotNull(lift);
		assertEquals("Peak 8 SuperConnect", lift.getName());
	}
	@Test
	void test_Routes() {
		assertNotNull(lift.getRoutes());
		assertTrue(lift.getRoutes().size()>0);
	}
	@Test
	void test_Peak() {
		assertNotNull(lift.getPeak());
		assertEquals("12998 ft", lift.getPeak().getElevation());
	}

}
