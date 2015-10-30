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
	public void testPossibleMove() {
		sfstd = new SpielFeld();
		assertTrue(sfstd.possibleMove(0, 1, Stein.WHITE)); //legal
		assertFalse(sfstd.possibleMove(3, 4, Stein.WHITE));//kein stein zu bewegen
		assertFalse(sfstd.possibleMove(0, 5, Stein.WHITE));//5 ist rot sicher
		assertTrue(sfstd.possibleMove(0, 1, Stein.WHITE));//weiss auf weiss
		assertTrue(sfstd.possibleMove(0, 6, Stein.WHITE));//6 weiss angreifbar
		assertTrue(sfstd.possibleMove(5, 6, Stein.BLACK));//schwarz schlägt weiss
		assertTrue(sfstd.possibleMove(5, 6, Stein.BLACK));
		assertTrue(sfstd.possibleMove(5, 7, Stein.BLACK));
		assertTrue(sfstd.possibleMove(0, 6, Stein.WHITE));
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
			if (sfstd.getDreiecke(i).getColor() == Stein.WHITE)
				assertSame(posw[i], sfstd.countOfTriangles(i));
			else
				assertSame(posb[i], sfstd.countOfTriangles(i));
		}
	}

	@Test
	public void testZug() {
		assertSame(0,sfstd.zug(0, 1, Stein.WHITE)); //legal
		assertSame(-1,sfstd.zug(3, 4, Stein.WHITE));//kein stein zu bewegen
		assertSame(-1,sfstd.zug(0, 5, Stein.WHITE));//5 ist rot sicher
		assertSame(0,sfstd.zug(0, 1, Stein.WHITE));//weiss auf weiss
		assertSame(0,sfstd.zug(1, 6, Stein.WHITE));//6 weiss angreifbar
		assertTrue(sfstd.isBarEmpty(new Spieler("T",Stein.WHITE)));
		assertSame(1,sfstd.zug(5, 6, Stein.BLACK));//schwarz schlägt weiss
		assertFalse(sfstd.isBarEmpty(new Spieler("T",Stein.WHITE)));
		assertSame(0,sfstd.zug(5, 6, Stein.BLACK));
		assertSame(0,sfstd.zug(6, 7, Stein.BLACK));
		assertSame(1,sfstd.zug(1, 6, Stein.WHITE));
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
		assertTrue(sfstd.isBarEmpty(new Spieler("T",Stein.WHITE)));
		assertTrue(sfstd.isBarEmpty(new Spieler("T",Stein.BLACK)));
	}

}
