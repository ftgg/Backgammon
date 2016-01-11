package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.implementation.Pitch;

public class IndexVerifierTest {

	IndexVerifier id;
	@Before
	public void setUp() throws Exception {
		id = new IndexVerifier();
	}

	@Test
	public void bIsValidtest() {
		assertTrue(id.bIsValid(0, new Pitch()));
		assertTrue(id.bIsValid(23, new Pitch()));
		assertTrue(id.bIsValid(IPitch.EXIT, new Pitch()));
		assertFalse(id.bIsValid(-5, new Pitch()));
		assertFalse(id.bIsValid(25, new Pitch()));
	}
	
	@Test
	public void aIsValidtest() {
		assertTrue(id.aIsValid(0, new Pitch()));
		assertTrue(id.aIsValid(23, new Pitch()));
		assertTrue(id.aIsValid(IPitch.BAR, new Pitch()));
		assertFalse(id.aIsValid(-5, new Pitch()));
		assertFalse(id.aIsValid(25, new Pitch()));
	}
	
	@Test
	public void isIndexValidTest() {
		assertTrue(id.isIndexValid(0,0, new Pitch()));
		assertFalse(id.isIndexValid(-5,3, new Pitch()));
		assertFalse(id.isIndexValid(3,26, new Pitch()));
		assertFalse(id.isIndexValid(26,-4, new Pitch()));
	}
	
	

}
