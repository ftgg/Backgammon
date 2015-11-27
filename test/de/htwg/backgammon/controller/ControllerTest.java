package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;
import de.htwg.backgammon.model.Stein;

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
	public void testParseAction() {
		assertTrue(2 == c.parseAction("3 5")[0] && 4 == c.parseAction("3 5")[1]);
		assertTrue(19 == c.parseAction("20 h")[0] && -1 == c.parseAction("20 h")[1]);
		assertTrue(-2 == c.parseAction("b 5")[0] && 4 == c.parseAction("b 5")[1]);
		assertTrue(-3 == c.parseAction("b")[0] && -3 == c.parseAction("b")[1]);

	}

	@Test
	public void testParseInt() {
		assertEquals(-3, c.parseInt("a"));
		assertEquals(9, c.parseInt("10"));
	}

	@Test
	public void testremoveThrowANDSpielerwechsel() {
		assertTrue(c.current.getColor() == Stein.WHITE);
		for (int i = 0; i < 30; i++) {
			c.wuerfeln();
			int erg[] = c.getZuege();
			if (erg[0] == erg[1]) {
				assertFalse(erg[0] == 0); // es wurde eine Zahl gew�rfelt
				c.removeThrow(0, erg[0]); // die zahl wird aus w�rfen gel�scht
				erg = c.getZuege();
				assertTrue(erg[0] == 0); // die Zahl ist tats�chlich aus w�rfen
											// gel�scht
			}
		}
	}

	@Test
	public void testController() {
		Controller c2 = new Controller();
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
		// TODO joah, gut getestet )= dazu brauch ich ein Observer?
	}

	@Test
	public void TestzuegeEmpty() {
		c.wuerfeln();
		assertFalse(c.zuegeEmpty());
		for (int i : c.getZuege())
			c.removeThrow(1, 1 + i);
		assertTrue(c.zuegeEmpty());
	}

	@Test
	public void TestSpielerWechsel() {
		Spieler[] s = c.getSpieler();
		c.spielerwechsel();
		assertSame(c.current, s[1]);
		c.spielerwechsel();
		assertNotSame(c.current, s[1]);
		assertSame(c.current, s[0]);
		c.spielerwechsel();
		assertNotSame(c.current, s[0]);
		assertSame(c.current, s[1]);

	}

	@Test
	public void TestotherPlayer() {
		Spieler[] s = c.getSpieler();
		assertSame(s[0], c.otherPlayer(s[1]));
	}

	@Test
	public void testIsDirectionValid() {
		assertFalse(c.isDirectionValid(2, 1));
		assertTrue(c.isDirectionValid(1, 2));
		for (int i : c.getZuege())
			c.removeThrow(1, 1 + i);
		c.spielerwechsel();
		assertFalse(c.isDirectionValid(1, 2));
		assertTrue(c.isDirectionValid(2, 1));

	}

	@Test
	public void testinDiceResult() {
		c = new Controller(6);
		c.getZuege();
		assertTrue(c.inDiceResult(10, 10 +c.getZuege()[0]));
		assertTrue(c.inDiceResult(23, SpielFeld.EXIT));
		assertFalse(c.inDiceResult(10, SpielFeld.EXIT));
	}

	@Test
	public void testgetDistance() {
		c = new Controller(6);
		assertEquals(5, c.getDistance(SpielFeld.BAR, 5));
		assertEquals(5, c.getDistance(19, SpielFeld.EXIT));
		for (int i : c.getZuege())
			c.removeThrow(1, 1 + i);
		c.spielerwechsel();
		assertEquals(3, c.getDistance(SpielFeld.BAR, 21));
		assertEquals(3, c.getDistance(2, SpielFeld.EXIT));
	}
	
	@Test
	public void testisExitMoveValid(){
		minC = new Controller(1);
		assertFalse(minC.isExitMoveValid(SpielFeld.EXIT));
		assertTrue(minC.isExitMoveValid(1));
		System.out.println(minC.spielZug(0, 1));
		
		for (int i : minC.getZuege())
			minC.removeThrow(1, 1 + i);
		minC.spielerwechsel();
		System.out.println(minC.spielZug(3, 2));
		
		for (int i : minC.getZuege())
			minC.removeThrow(1, 1 + i);
//		minC.spielerwechsel();
//		
//		assertFalse(minC.isExitMoveValid(SpielFeld.EXIT));
//		System.out.println(minC.spielZug(1, 3));
//		assertTrue(minC.isExitMoveValid(SpielFeld.EXIT));
		
	  
	}

}
