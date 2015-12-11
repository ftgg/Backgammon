package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ActionParserTest {

	ActionParser c;
	@Before
	public void setUp() throws Exception {
		c = new ActionParser();
	}

	@Test
	public void testParseAction() {
		assertTrue(2 == c.parse("3 5")[0] && 4 == c.parse("3 5")[1]);
		assertTrue(19 == c.parse("20 h")[0] && -1 == c.parse("20 h")[1]);
		assertTrue(-2 == c.parse("b 5")[0] && 4 == c.parse("b 5")[1]);
		assertTrue(-3 == c.parse("b")[0] && -3 == c.parse("b")[1]);
		assertSame(Controller.NEXT , c.parse("n")[0]);

	}



}
