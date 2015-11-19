package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

	Controller c;

	@Before
	public void setUp() throws Exception {
		c = new Controller("Test2");
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

	}

	@Test
	public void testParseInt() {
		assertEquals(-3, c.parseInt("a"));
		assertEquals(9, c.parseInt("10"));
	}

	// TODO @Test
	public void testLoescheWurf() {
		// vorraussetzung auf feld 5 liegt ein stein und auf feld 10 ist es leer
		//TODO diese vorraussetzung schaffen
		for (int i = 0; i < 30; i++) {
			c.wuerfeln();
			int erg[] = c.getZuege();
			if (erg[0] == 5 || erg[1] == 5) {
				assertTrue(c.verifyMove(5, 10));
				c.removeThrow(5, 10);
				erg = c.getZuege();
				assertTrue(erg[0] == 0 || erg[1] == 0);
			} else {
				assertFalse(c.verifyMove(5, 10));
			}
		}
	}

	@Test
	public void verifyMove() {
		
	}

}
