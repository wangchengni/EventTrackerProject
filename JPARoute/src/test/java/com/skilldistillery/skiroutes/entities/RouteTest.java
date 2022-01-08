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

class RouteTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Route route;
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
		route =em.find(Route.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		route = null;
		
	}

	@Test
	void test_Peak() {
		assertNotNull(route.getPeak());
		assertEquals("Peak 8", route.getPeak().getName());
	}
	@Test
	void test_Lift() {
		assertNotNull(route.getLift());
		assertEquals("Peak 8 SuperConnect", route.getLift().getName());
	}
	@Test
	void test() {
		assertNotNull(route);
		assertEquals("Last Hoot", route.getName());
	}
	@Test
	void test_Conditions() {
		assertNotNull(route.getSnowConditions());
		assertTrue(route.getSnowConditions().size()>0);
	}

}
