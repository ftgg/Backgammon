package de.htwg.backgammon.model;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.Spieler;
import junit.framework.TestCase;

public class SpielerTest extends TestCase {
	Spieler s;
	Spieler s2;
	Spieler s3;
	final String name = "Samuel";
	final String name2 = "Martin";
	final String name3 = "Max";

	@Before
	public void setUp() throws Exception {
		s = new Spieler(name, 5);
		s2 = new Spieler(name2, Stein.White);
		s3 = new Spieler(name3, Stein.Black);
	}

	@Test
	public void testGetName() {
		// assertEquals(name, s.getName());
		assertEquals(name2, s2.getName());
		assertEquals(name3, s3.getName());
	}

	@Test
	public void testGetColor() {
		assertEquals(Stein.White, s2.getColor());
		assertEquals(Stein.Black, s3.getColor());
	}

}
