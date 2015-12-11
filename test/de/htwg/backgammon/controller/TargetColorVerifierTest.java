package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.Pitch;
import de.htwg.backgammon.model.implementation.Player;


public class TargetColorVerifierTest {

	TargetColorVerifier tcv;
	Player spielerw;
	Player spielerb;
	Pitch sf;
	@Before
	public void setUp() throws Exception {
		tcv = new TargetColorVerifier();
		spielerb = new Player("b",TokenColor.BLACK);
		spielerw = new Player("a",TokenColor.WHITE);
		sf = new Pitch(6);
	}

	@Test
	public void testPossibleMove() {
		assertTrue(tcv.isTargetColorValid(0, 1, spielerw,sf)); // legal
		assertFalse(tcv.isTargetColorValid(3, 4, spielerw,sf));// kein stein zu
															// bewegen
		assertFalse(tcv.isTargetColorValid(0, 5, spielerw,sf));// 5 ist rot sicher
		assertTrue(tcv.isTargetColorValid(0, 1, spielerw,sf));// weiss auf weiss
		assertTrue(tcv.isTargetColorValid(0, 6, spielerw,sf));// 6 weiss angreifbar
		assertTrue(tcv.isTargetColorValid(5, 6, spielerb,sf));// schwarz schlägt
															// weiss
		assertTrue(tcv.isTargetColorValid(5, 6, spielerb,sf));
		assertTrue(tcv.isTargetColorValid(5, 7, spielerb,sf));
		assertTrue(tcv.isTargetColorValid(0, 6, spielerw,sf));
	}

}
