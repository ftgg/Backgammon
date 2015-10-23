package de.htwg.backgammon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SpielFeldTest {

	private SpielFeld sf;
	
	@Before
	public void setUp() throws Exception {
		sf = new SpielFeld();
	}

	@Test
	public void testSpielFeld() {
		assertNotNull(sf);
	}
	
	public void testGetSize(){
		assertTrue(sf.getSize() == 24);
	}
	
	public void testGetDreieck(){
		
	}

}
