package de.htwg.backgammon.model;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.GameState;
import de.htwg.backgammon.model.implementation.Pitch;
import de.htwg.backgammon.model.implementation.Player;

import junit.framework.TestCase;

public class GameStateTest extends TestCase {

	GameState gs;
	Pitch sf;
	Player spielerw , spielerb;

	@Before
	public void setUp() throws Exception {
		sf = new Pitch();
		spielerw = new Player("Hund", TokenColor.WHITE);
		spielerb = new Player("Hund", TokenColor.BLACK);
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
		
		sf.move(0, 6, spielerw);
		sf.move(5, 6, spielerb); //Schwarz schlägt Weiss
		assertFalse(sf.isBarEmpty(spielerw));
		assertTrue(sf.isBarEmpty(spielerb));
		gs = new GameState(sf, new int[] { 1, 1, 1, 1 }, "NEIN", spielerw, false);
		assertEquals(0, gs.getBlackBar());
		assertEquals(1, gs.getWhiteBar());
	}
	

}
