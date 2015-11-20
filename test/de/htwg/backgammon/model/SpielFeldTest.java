package de.htwg.backgammon.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Stein;

public class SpielFeldTest {

	private SpielFeld sfstd, sfs;
	private int size; // size of (pitch/4)
	private Spieler spielerw, spielerb;

	@Before
	public void setUp() throws Exception {
		size = 2;
		sfstd = new SpielFeld();
		sfs = new SpielFeld(size);
		spielerw = new Spieler("Jan", Stein.WHITE);
		spielerb = new Spieler("Herbert", Stein.BLACK);
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
		assertTrue(sfstd.isMovePossible(0, 1, spielerw)); // legal
		assertFalse(sfstd.isMovePossible(3, 4, spielerw));// kein stein zu
															// bewegen
		assertFalse(sfstd.isMovePossible(0, 5, spielerw));// 5 ist rot sicher
		assertTrue(sfstd.isMovePossible(0, 1, spielerw));// weiss auf weiss
		assertTrue(sfstd.isMovePossible(0, 6, spielerw));// 6 weiss angreifbar
		assertTrue(sfstd.isMovePossible(5, 6, spielerb));// schwarz schlägt
															// weiss
		assertTrue(sfstd.isMovePossible(5, 6, spielerb));
		assertTrue(sfstd.isMovePossible(5, 7, spielerb));
		assertTrue(sfstd.isMovePossible(0, 6, spielerw));
	}

	@Test
	public void testGetStonesOnField(){
		assertEquals(1,sfs.getStonesOnField()[0]);
		assertEquals(1,sfs.getStonesOnField()[1]);
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
		assertSame(0, sfstd.zug(0, 1, spielerw)); // legal
		assertSame(0, sfstd.zug(0, 1, spielerw));// weiss auf weiss
		assertSame(0, sfstd.zug(1, 6, spielerw));// 6 weiss angreifbar
		assertTrue(sfstd.isBarEmpty(new Spieler("T", Stein.WHITE)));
		assertSame(1, sfstd.zug(5, 6, spielerb));// schwarz schlägt weiss
		assertFalse(sfstd.isBarEmpty(new Spieler("T", Stein.WHITE)));
		assertSame(0, sfstd.zug(5, 6, spielerb));
		assertSame(0, sfstd.zug(6, 7, spielerb));
		assertSame(1, sfstd.zug(1, 6, spielerw));
	}

	@Test
	public void testremoveStone(){
		assertEquals(0,sfs.zug(0, 1,spielerb));
		assertEquals(111,sfs.zug(0, sfs.EXIT,spielerb));
		assertEquals(111,sfs.zug(0, sfs.EXIT,spielerw));
	}
	
	
	@Test
	public void testIsEmpty() {

		for (int i = 0; i < size * 4; i++) {
			if (i == 0 || i == size * 4 - 1)
				assertFalse(sfs.isEmpty(i));
			else
				assertTrue(sfs.isEmpty(i));
		}

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
		assertTrue(sfstd.isBarEmpty(new Spieler("T", Stein.WHITE)));
		assertTrue(sfstd.isBarEmpty(new Spieler("T", Stein.BLACK)));
	}

	@Test
	public void testindexInBase() {
		assertTrue(sfstd.indexInHome(23, spielerw));
		assertFalse(sfstd.indexInHome(23, spielerb));
		assertFalse(sfstd.indexInHome(4, spielerw));
		assertTrue(sfstd.indexInHome(0, spielerb));
		assertTrue(sfstd.indexInHome(5, spielerb));
		assertFalse(sfstd.indexInHome(6, spielerb));
		assertFalse(sfstd.indexInHome(7, spielerw));
		assertFalse(sfstd.indexInHome(10, spielerb));
		assertTrue(sfstd.indexInHome(23, spielerw));
		assertTrue(sfstd.indexInHome(18, spielerw));
		assertFalse(sfstd.indexInHome(17, spielerw));
	}

	@Test
	public void testallHome() {
		Spieler jan = new Spieler("Jan", Stein.WHITE);
		assertFalse(sfs.allHome(jan));
		sfs.zug(0,size*4-1, jan);
		assertTrue(sfs.allHome(jan));
	}
}
