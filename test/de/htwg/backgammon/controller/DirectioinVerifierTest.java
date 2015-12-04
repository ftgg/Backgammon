package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.implementation.SpielFeld;
import de.htwg.backgammon.model.implementation.Spieler;
import de.htwg.backgammon.model.implementation.Stein;

public class DirectioinVerifierTest {
	DirectionVerifier dv;
	SpielFeld sf;
	Spieler s1;
	Spieler s2;
	@Before
	public void setUp() throws Exception {
		dv = new DirectionVerifier();
		sf = new SpielFeld(6);
		s1 = new Spieler("w",Stein.WHITE);
		s2 = new Spieler("b",Stein.BLACK);
	}

	@Test
	public void testIsDirectionValid() {
		assertFalse(dv.isDirectionValid(2, 1,s1));
		assertTrue(dv.isDirectionValid(1, 2,s1));

		assertFalse(dv.isDirectionValid(1, 2,s2));
		assertTrue(dv.isDirectionValid(2, 1,s2));
	}

}
