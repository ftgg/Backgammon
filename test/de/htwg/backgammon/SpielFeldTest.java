package de.htwg.backgammon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SpielFeldTest {

	private SpielFeld sfstd, sfs;
	private int size; // size of (pitch/4)

	@Before
	public void setUp() throws Exception {
		size = 2;
		sfstd = new SpielFeld();
		sfs = new SpielFeld(size);
	}

	@Test
	public void testSpielFeld() {
		assertNotNull(sfstd);
		assertNotNull(sfs);
	}

	@Test
	public void testGetSize() {
		assertTrue(sfstd.getSize() == 24);
		assertTrue(sfs.getSize() == size * 4);
	}

	@Test
	public void testGetDreieck() {
		for (int i = sfs.getSize() - 1; i >= 0; i--) {
			assertNotNull(sfs.getDreiecke(i));
		}
	}
	
	@Test
	public void testZug(){
		
	}

}
