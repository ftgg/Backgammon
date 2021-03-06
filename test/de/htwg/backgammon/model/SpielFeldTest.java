package de.htwg.backgammon.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.implementation.GameState;
import de.htwg.backgammon.model.implementation.Pitch;
import de.htwg.backgammon.model.implementation.Player;


public class SpielFeldTest {

	private Pitch sfstd, sfs;
	private int size; // size of (pitch/4)
	private Player spielerw, spielerb;

	@Before
	public void setUp() throws Exception {
		size = 2;
		sfstd = new Pitch();
		sfs = new Pitch(GameState.getTestGameState(size));
		spielerw = new Player("Jan", TokenColor.WHITE);
		spielerb = new Player("Herbert", TokenColor.BLACK);
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
			if (sfstd.getTriangleColor(i) == TokenColor.WHITE)
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
		assertTrue(sfstd.isBarEmpty(new Player("T", TokenColor.WHITE)));
		assertSame(1, sfstd.move(5, 6, spielerb));// schwarz schl�gt weiss
		assertFalse(sfstd.isBarEmpty(new Player("T", TokenColor.WHITE)));
		
		assertSame(0, sfstd.move(5, 6, spielerb));
		assertSame(0, sfstd.move(6, 7, spielerb));
		assertSame(1, sfstd.move(1, 6, spielerw));
		
		assertSame(1, sfs.move(0,7,spielerw));
		assertFalse(sfs.isBarEmpty(spielerb));
		assertSame(1 , sfs.getBarBlack().count());
		assertSame(0 , sfs.getBarWhite().count());
		assertSame(TokenColor.NONE, sfs.getBarWhite().getColor());
		assertSame(1, sfs.move(IPitch.BAR,7,spielerb));
		assertSame(0 , sfs.getBarBlack().count());
		assertSame(1 , sfs.getBarWhite().count());
		assertSame(0, sfs.move(IPitch.BAR,3,spielerw));
	}

	@Test
	public void testremoveStone(){
		assertEquals(0,sfs.move(0, 1,spielerb));
		assertEquals(111,sfs.move(0, IPitch.EXIT,spielerb));
		assertEquals(111,sfs.move(0, IPitch.EXIT,spielerw));
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
		assertTrue(sfstd.isBarEmpty(new Player("T", TokenColor.WHITE)));
		assertTrue(sfstd.isBarEmpty(new Player("T", TokenColor.BLACK)));
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
		Player jan = new Player("Jan", TokenColor.WHITE);
		assertFalse(sfs.allHome(jan));
		sfs.move(0,size*4-1, jan);
		assertTrue(sfs.allHome(jan));
	}
}
