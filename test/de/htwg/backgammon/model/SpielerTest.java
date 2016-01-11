package de.htwg.backgammon.model;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.implementation.Player;
import junit.framework.TestCase;

public class SpielerTest extends TestCase {
	Player s;
	Player s2;
	Player s3;
	final String name = "Samuel";
	final String name2 = "Martin";
	final String name3 = "Max";

	@Before
	public void setUp() throws Exception {
		s = new Player(name, TokenColor.NONE);
		s2 = new Player(name2, TokenColor.WHITE);
		s3 = new Player(name3, TokenColor.BLACK);
	}

	@Test
	public void testGetName() {
		// assertEquals(name, s.getName());
		assertEquals(name2, s2.getName());
		assertEquals(name3, s3.getName());
		assertEquals(s3.toString(), "Player(BLACK) :Max");
	}

	@Test
	public void testGetColor() {
		assertEquals(TokenColor.WHITE, s2.getColor());
		assertEquals(TokenColor.BLACK, s3.getColor());
	}
	
	@Test
	public void testEquals(){
		assertEquals(s.hashCode(),new Player(name, TokenColor.NONE).hashCode());
		assertFalse(s.equals(new Integer(2)));
		assertFalse(s.equals(s2));
	}

}
