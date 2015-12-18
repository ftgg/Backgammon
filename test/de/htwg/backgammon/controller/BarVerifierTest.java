package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.GameState;
import de.htwg.backgammon.model.implementation.Pitch;
import de.htwg.backgammon.model.implementation.Player;


public class BarVerifierTest {
	BarVerifier bv;
	Player s1;
	Player s2;
	Player c;
	Pitch sf;
	@Before
	public void setUp() throws Exception {
		bv = new BarVerifier();
		sf = new Pitch(GameState.getTestGameState(1));
		s1 = new Player("b", TokenColor.BLACK);
		s2 = new Player("w", TokenColor.WHITE);
		c = s1;
	}

	
	@Test
	public void TestBarmove() {
		assertFalse(bv.checkBarmove(IPitch.BAR, 0, sf, c, s1, s2));
		sf.move(3, 0, s1);
		c = s2;
		assertFalse(bv.checkBarmove(IPitch.BAR, 2, sf, c, s1, s2));
		assertTrue(bv.checkBarmove(IPitch.BAR, 0, sf, c, s1, s2));
		
	}

	@Test
	public void TestotherPlayer() {
		assertSame(s2, bv.otherPlayer(c,s1,s2));
		c = s2;
		assertSame(s1, bv.otherPlayer(c,s1,s2));
	}
	
}
