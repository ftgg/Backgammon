package de.htwg.backgammon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DreieckTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testDreieck() {
		Dreieck dreieck = new Dreieck();
		assertNotNull(dreieck);
	}

}
