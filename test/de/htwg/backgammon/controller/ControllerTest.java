package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.IPlayer;
import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.Dice;
import de.htwg.backgammon.model.implementation.GameState;
import de.htwg.backgammon.model.implementation.Pitch;
import de.htwg.backgammon.model.implementation.Player;


public class ControllerTest {

	Controller c;
	Controller minC;

	@Before
	public void setUp() throws Exception {
		c = new Controller(6);
		minC = new Controller(1);
	}

	@Test
	public void wuerfeln() {

		for (int j = 0; j < 36; j++) {
			c.wuerfeln();
			int[] erg = c.getZuege();
			int[] cur = c.getWuerfelC();
			for (int i = 0; i < 2; i++) {
				assertEquals(cur[i], erg[i]);
			}

			if (cur[0] == cur[1]) {
				assertEquals(erg[2], cur[0]);
				assertEquals(erg[3], cur[1]);
			} else {
				assertEquals(erg[2], 0);
				assertEquals(erg[3], 0);
			}

		}

	}


	@Test
	public void testController() {
		Controller c2 = new Controller(new Pitch(), new Dice(), 
				new Player("S1", TokenColor.BLACK), 
				new Player("S2", TokenColor.WHITE));
		assertNotNull(c2.getWuerfelC());
	}

	@Test
	public void testdoAction() {
		c.doAction("hallo welt");
		c.doAction("0 5");
		c.doAction("1 5");
		c.wuerfeln();
		int erg[] = c.getZuege();
		c.doAction("1 " + (erg[0] + 1));
		c.doAction("0 b");
		c.doAction("n");
		// TODO joah, gut getestet )= dazu brauch ich ein Observer?
	}

	@Test
	public void TestzuegeEmpty() {
		c.wuerfeln();
		assertFalse(c.zuegeEmpty());
	}

	@Test
	public void TestSpielerWechsel() {
		IPlayer[] s = c.getSpieler();
		c.spielerwechsel();
		assertSame(c.getCurrent(), s[1]);
		c.spielerwechsel();
		assertNotSame(c.getCurrent(), s[1]);
		assertSame(c.getCurrent(), s[0]);
		c.spielerwechsel();
		assertNotSame(c.getCurrent(), s[0]);
		assertSame(c.getCurrent(), s[1]);
	}
	
	@Test
	public void Testsetclick(){
		c.setclick(1);
		c.setclick(2);
		assertEquals(c.convertToMoveString(1, 12),"1 12");
		assertEquals(c.convertToMoveString(1, 1),"1 h");
		assertEquals(c.convertToMoveString(24, 24),"24 h");
	}

	@Test
	public void undo(){
		minC = new Controller(1);
		minC.doAction("1 2");
		GameState gs = minC.getCurrentGameState();
		minC.doAction("2 3");
		minC.undo();
		GameState gs2 = minC.getCurrentGameState();
		assertSame(gs,gs2);
		
	}


	

	


}
