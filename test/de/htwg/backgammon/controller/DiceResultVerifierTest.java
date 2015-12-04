package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;
import de.htwg.backgammon.model.Stein;
import de.htwg.backgammon.model.Wuerfel;

public class DiceResultVerifierTest {

	DiceResultVerifier drv;
	Spieler spielerw;
	Spieler spielerb;
	SpielFeld sf;
	Wuerfel w;
	@Before
	public void setUp() throws Exception {
		drv = new DiceResultVerifier();
		spielerb = new Spieler("b",Stein.BLACK);
		spielerw = new Spieler("a",Stein.WHITE);
		sf = new SpielFeld(6);
		w = new Wuerfel();
	}

	@Test
	public void testinDiceResult() {
		w.wuerfeln();
		
		assertTrue(drv.inDiceResult(10, 10 + w.getCurrentCubeNumbers()[0],w.getCurrentCubeNumbers(),sf,spielerb));
		assertTrue(drv.inDiceResult(23, SpielFeld.EXIT,w.getCurrentCubeNumbers(),sf,spielerw));
		assertFalse(drv.inDiceResult(10, SpielFeld.EXIT,w.getCurrentCubeNumbers(), sf, spielerb));
	}

	@Test
	public void testgetDistance() {
		assertEquals(5, drv.getDistance(SpielFeld.BAR, 5,sf,spielerw));
		assertEquals(5, drv.getDistance(19, SpielFeld.EXIT,sf,spielerw));

		assertEquals(3, drv.getDistance(SpielFeld.BAR, 21,sf,spielerb));
		assertEquals(3, drv.getDistance(2, SpielFeld.EXIT,sf,spielerb));
	}

}
