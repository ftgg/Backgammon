package de.htwg.backgammon.controller;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;
import de.htwg.backgammon.model.Stein;
import junit.framework.TestCase;

public class GameStateTest extends TestCase {

	GameState gs;
	SpielFeld sf;
	Spieler spielerw , spielerb;

	@Before
	public void setUp() throws Exception {
		sf = new SpielFeld();
		spielerw = new Spieler("Hund", Stein.WHITE);
		spielerb = new Spieler("Hund", Stein.BLACK);
		gs = new GameState(sf, new int[] { 0, 1, 2, 3 }, spielerw);

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

	@Test
	public void testGameNotFinished(){
		assertFalse(gs.getGameFinished());
	}
	
	@Test
	public void testZuege() {
		for (int i = 0; i < 4; i++) {
			assertEquals(i, gs.getZuege()[i]);
		}
	}

	@Test
	public void testMessageAndSpieler() {
		assertEquals("Update", gs.getMessage());
		assertEquals(spielerw,gs.getCurrent());
	}

	@Test
	public void testBars() {
		// TODO testen bei inhalt auf der bar

		assertEquals(0, gs.getWhiteBar());
		assertEquals(0, gs.getBlackBar());
		
		sf.zug(0, 6, spielerw);
		sf.zug(5, 6, spielerb); //Schwarz schlägt Weiss
		assertFalse(sf.isBarEmpty(spielerw));
		assertTrue(sf.isBarEmpty(spielerb));
		gs = new GameState(sf, new int[] { 1, 1, 1, 1 }, "NEIN", spielerw, false);
		assertEquals(0, gs.getBlackBar());
		assertEquals(1, gs.getWhiteBar());
	}
	

}
