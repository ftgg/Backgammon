package de.htwg.backgammon.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Stein;

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
	public void testFill() {
		Integer[] posw = { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0, 0 };
		Integer[] posb = { 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 };
		for (int i = 0; i < 24; i++) {
			if (sfstd.getDreiecke(i).getColor() == Stein.White)
				assertSame(posw[i], sfstd.count_of_D(i));
			else
				assertSame(posb[i], sfstd.count_of_D(i));
		}
	}

	@Test
	public void testZug() {
		assertSame(0,sfstd.zug(0, 1, Stein.White)); //legal
		assertSame(-1,sfstd.zug(3, 4, Stein.White));//kein stein zu bewegen
		assertSame(-1,sfstd.zug(0, 5, Stein.White));//5 ist rot sicher
		assertSame(0,sfstd.zug(0, 1, Stein.White));//weiss auf weiss
		assertSame(0,sfstd.zug(1, 6, Stein.White));//6 weiss angreifbar
		assertTrue(sfstd.isBarEmpty(Stein.White));
		assertSame(1,sfstd.zug(5, 6, Stein.Black));//schwarz schlägt weiss
		assertFalse(sfstd.isBarEmpty(Stein.White));
		assertSame(0,sfstd.zug(5, 6, Stein.Black));
		assertSame(0,sfstd.zug(6, 7, Stein.Black));
		assertSame(1,sfstd.zug(1, 6, Stein.White));
	}

	@Test
	public void testIsEmpty() {
		
		for (int i = 0; i < size * 4; i++)
			assertTrue(sfs.isEmpty(i));
		
		for (int i = 0; i < 24; i++)
			switch (i) {
			case 0:
			case 5:
			case 7:
			case 11:
			case 12:
			case 16:
			case 18:
			case 23:
				assertFalse(sfstd.isEmpty(i));
				break;
			default:
				assertTrue(sfstd.isEmpty(i));
			}
	}

	@Test
	public void testIsBarEmpty() {
		assertTrue(sfstd.isBarEmpty(Stein.White));
		assertTrue(sfstd.isBarEmpty(Stein.Black));
	}

}
