package de.htwg.backgammon;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class WuerfelTest extends TestCase{

	@Test
	public void testWuerfeln() {
		Wuerfel w = new Wuerfel();
		//zahl zwischen [1;6]
		int[] a = w.wuerfeln();
		assertNotNull(a);
		
		assertTrue(a[0]<7);
		assertTrue(a[0]>0);
		assertTrue(a[1]<7);
		assertTrue(a[1]>0);
	}

}
