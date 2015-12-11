package de.htwg.backgammon.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.implementation.Dice;
import junit.framework.TestCase;

public class WuerfelTest extends TestCase {
	Dice w;

	@Before
	public void setUp() throws Exception {
		w = new Dice();
		try {
			Dice C = new Dice(-2, 5);
			fail("Excetion");
		} catch (Exception e) {

		}
	}

	@Test
	public void testGetCurrent() {
		assertSame(w.wuerfeln(), w.getCurrentCubeNumbers());
	}

	@Test
	public void testisPasch() {
		for (int i = 0; i < 37; i++) {
			w.wuerfeln();
			assertSame(w.isDoublets(), w.getCurrentCubeNumbers()[0] == w.getCurrentCubeNumbers()[1]);
		}
	}

	@Test
	public void testWuerfeln() {

		// zahl zwischen [1;6]
		int[] a = w.wuerfeln();
		assertNotNull(a);

		assertTrue(a[0] < 7);
		assertTrue(a[0] > 0);
		assertTrue(a[1] < 7);
		assertTrue(a[1] > 0);
		// w = new Wuerfel(6,1);

	}

	@Test
	public void testMin() {
		assertNotNull(w.getMin());
		assertTrue(w.getMin() > 0 && w.getMax() > w.getMin());
	}

	@Test
	public void testMax() {
		assertNotNull(w.getMax());
		assertTrue(w.getMax() > 0 && w.getMax() > w.getMin());
	}
}
