package de.htwg.backgammon.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.implementation.SpielFeld;
import de.htwg.backgammon.model.implementation.Spieler;
import de.htwg.backgammon.model.implementation.Stein;

public class SpielFeldTest {

	private testPitch sfstd, sfs;
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
	public void testGetStonesOnField(){
		assertEquals(1,sfs.getTokensOnTriangle()[0]);
		assertEquals(1,sfs.getTokensOnTriangle()[1]);
	}
	
	@Test
	public void testGetDreieck() {
		for (int i = sfs.getSize() - 1; i >= 0; i--) {
			assertNotNull(sfs.getTriangle(i));
		}
	}

	@Test
	public void testFill() {
		Integer[] posw = { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0, 0 };
		Integer[] posb = { 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 };
		for (int i = 0; i < 24; i++) {
			if (sfstd.getTriangle(i).getColor() == Stein.WHITE)
				assertSame(posw[i], sfstd.countOfTriangle(i));
			else
				assertSame(posb[i], sfstd.countOfTriangle(i));
		}
	}

	@Test
	public void testZug() {
		assertSame(0, sfstd.move(0, 1, spielerw)); // legal
		assertSame(0, sfstd.move(0, 1, spielerw));// weiss auf weiss
		assertSame(0, sfstd.move(1, 6, spielerw));// 6 weiss angreifbar
		assertTrue(sfstd.isBarEmpty(new Spieler("T", Stein.WHITE)));
		assertSame(1, sfstd.move(5, 6, spielerb));// schwarz schlägt weiss
		assertFalse(sfstd.isBarEmpty(new Spieler("T", Stein.WHITE)));
		
		assertSame(0, sfstd.move(5, 6, spielerb));
		assertSame(0, sfstd.move(6, 7, spielerb));
		assertSame(1, sfstd.move(1, 6, spielerw));
		
		assertSame(1, sfs.move(0,7,spielerw));
		assertFalse(sfs.isBarEmpty(spielerb));
		assertSame(1 , sfs.getBarblack().count());
		assertSame(0 , sfs.getBarwhite().count());
		assertSame(Triangle.NONE, sfs.getBarwhite().getColor());
		assertSame(1, sfs.move(Pitch.BAR,7,spielerb));
		assertSame(0 , sfs.getBarblack().count());
		assertSame(1 , sfs.getBarwhite().count());
		assertSame(0, sfs.move(Pitch.BAR,3,spielerw));
	}

	@Test
	public void testremoveStone(){
		assertEquals(0,sfs.move(0, 1,spielerb));
		assertEquals(111,sfs.move(0, Pitch.EXIT,spielerb));
		assertEquals(111,sfs.move(0, Pitch.EXIT,spielerw));
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
		sfs.move(0,size*4-1, jan);
		assertTrue(sfs.allHome(jan));
	}
}
