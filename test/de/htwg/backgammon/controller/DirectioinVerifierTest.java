package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.Pitch;
import de.htwg.backgammon.model.implementation.Player;


public class DirectioinVerifierTest {
	DirectionVerifier dv;
	Pitch sf;
	Player s1;
	Player s2;
	@Before
	public void setUp() throws Exception {
		dv = new DirectionVerifier();
		sf = new Pitch(6);
		s1 = new Player("w",TokenColor.WHITE);
		s2 = new Player("b",TokenColor.BLACK);
	}

	@Test
	public void testIsDirectionValid() {
		assertFalse(dv.isDirectionValid(2, 1,s1));
		assertTrue(dv.isDirectionValid(1, 2,s1));

		assertFalse(dv.isDirectionValid(1, 2,s2));
		assertTrue(dv.isDirectionValid(2, 1,s2));
	}

}
