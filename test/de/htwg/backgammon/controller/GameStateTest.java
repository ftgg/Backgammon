package de.htwg.backgammon.controller;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.SpielFeld;
import junit.framework.TestCase;

public class GameStateTest extends TestCase {

	GameState gs;
	SpielFeld sf;

	@Before
	public void setUp() throws Exception {
		sf = new SpielFeld();
		gs = new GameState(sf, new int[] { 0, 1, 2, 3 });

	}

	@Test
	public void testfillArrays() {
		int[] posw = { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0, 0 };
		int[] posb = { 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 };
		for (int i = 0; i < 24; i++) {
			assertEquals(posb[i], gs.getBlackStones()[i]);
			assertEquals(posw[i], gs.getWhiteStones()[i]);
		}
	}


}
