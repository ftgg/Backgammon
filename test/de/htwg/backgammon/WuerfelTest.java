package de.htwg.backgammon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class WuerfelTest extends TestCase {
	Wuerfel w;

	@Before
	public void setUp() throws Exception {
		w = new Wuerfel();
		try {
			Wuerfel C = new Wuerfel(-2, 5);
			fail("Excetion");
		} catch (Exception e) {

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
