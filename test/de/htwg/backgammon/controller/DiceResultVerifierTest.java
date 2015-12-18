package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.Pitch;
import de.htwg.backgammon.model.implementation.Player;

import de.htwg.backgammon.model.implementation.Dice;

public class DiceResultVerifierTest {

	DiceResultVerifier drv;
	Player spielerw;
	Player spielerb;
	Pitch sf;
	Dice w;
	@Before
	public void setUp() throws Exception {
		drv = new DiceResultVerifier();
		spielerb = new Player("b",TokenColor.BLACK);
		spielerw = new Player("a",TokenColor.WHITE);
		sf = new Pitch();
		w = new Dice();
	}

	@Test
	public void testinDiceResult() {
		w.wuerfeln();
		
		assertTrue(drv.inDiceResult(10, 10 + w.getCurrentCubeNumbers()[0],w.getCurrentCubeNumbers(),sf,spielerb));
		assertTrue(drv.inDiceResult(23, Pitch.EXIT,w.getCurrentCubeNumbers(),sf,spielerw));
		assertFalse(drv.inDiceResult(10, Pitch.EXIT,w.getCurrentCubeNumbers(), sf, spielerb));
	}

	@Test
	public void testgetDistance() {
		assertEquals(5, drv.getDistance(Pitch.BAR, 5,sf,spielerw));
		assertEquals(5, drv.getDistance(19, Pitch.EXIT,sf,spielerw));

		assertEquals(3, drv.getDistance(Pitch.BAR, 21,sf,spielerb));
		assertEquals(3, drv.getDistance(2, Pitch.EXIT,sf,spielerb));
	}

}
