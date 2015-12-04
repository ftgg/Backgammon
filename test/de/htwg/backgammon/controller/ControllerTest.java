package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.Player;
import de.htwg.backgammon.model.implementation.Stein;

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
		assertSame(-4 , c.parseAction("n")[0]);

	}

	@Test
	public void testParseInt() {
		assertEquals(-3, c.parseInt("a"));
		assertEquals(9, c.parseInt("10"));
		assertEquals(Controller.NEXT, -4);
	}

	@Test
	public void testremoveThrowANDSpielerwechsel() {
		assertTrue(c.getCurrent().getColor() == Stein.WHITE);
		for (int i = 0; i < 30; i++) {
			c.wuerfeln();
			int erg[] = c.getZuege();
			if (erg[0] == erg[1]) {
				assertFalse(erg[0] == 0); // es wurde eine Zahl gewürfelt
				c.removeThrow(0, erg[0]); // die zahl wird aus würfen gelöscht
				erg = c.getZuege();
				assertTrue(erg[0] == 0); // die Zahl ist tatsächlich aus würfen
											// gelöscht
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
		c.doAction("0 b");
		c.doAction("n");
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
		Player[] s = c.getSpieler();
		c.spielerwechsel();
		assertSame(c.getCurrent(), s[1]);
		c.spielerwechsel();
		assertNotSame(c.getCurrent(), s[1]);
		assertSame(c.getCurrent(), s[0]);
		c.spielerwechsel();
		assertNotSame(c.getCurrent(), s[0]);
		assertSame(c.getCurrent(), s[1]);

	}




	

	


}
