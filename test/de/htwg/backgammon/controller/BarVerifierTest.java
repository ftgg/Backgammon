package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.implementation.SpielFeld;
import de.htwg.backgammon.model.implementation.Spieler;
import de.htwg.backgammon.model.implementation.Stein;

public class BarVerifierTest {
	BarVerifier bv;
	Spieler s1;
	Spieler s2;
	Spieler c;
	SpielFeld sf;
	@Before
	public void setUp() throws Exception {
		bv = new BarVerifier();
		sf = new SpielFeld(1);
		s1 = new Spieler("b", Stein.BLACK);
		s2 = new Spieler("w", Stein.WHITE);
		c = s1;
	}

	
	@Test
	public void TestBarmove() {
		assertFalse(bv.checkBarmove(Pitch.BAR, 0, sf, c, s1, s2));
		sf.move(3, 0, s1);
		c = s2;
		assertFalse(bv.checkBarmove(Pitch.BAR, 2, sf, c, s1, s2));
		assertTrue(bv.checkBarmove(Pitch.BAR, 0, sf, c, s1, s2));
		
	}

	@Test
	public void TestotherPlayer() {
		assertSame(s2, bv.otherPlayer(c,s1,s2));
		c = s2;
		assertSame(s1, bv.otherPlayer(c,s1,s2));
	}
	
}
