package de.htwg.backgammon;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class SpielerTest extends TestCase {
	Spieler s;
	final String name = "Samuel";

	@Before
	public void setUp() throws Exception {
		s = new Spieler(name);
	}

	@Test
	public void testGetName() {
		assertEquals(name, s.getName());
	}

}
