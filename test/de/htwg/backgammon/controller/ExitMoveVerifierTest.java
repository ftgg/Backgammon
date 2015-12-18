package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.GameState;
import de.htwg.backgammon.model.implementation.Pitch;
import de.htwg.backgammon.model.implementation.Player;


public class ExitMoveVerifierTest {

	ExitMoveVerifier ev;
	Pitch sf;
	Player s1;
	@Before
	public void setUp() throws Exception {
		ev = new ExitMoveVerifier();
		sf = new Pitch(GameState.getTestGameState(1));
		s1 = new Player("b", TokenColor.BLACK);
	}

	@Test
	public void testisExitMoveValid(){	
		assertFalse(ev.isExitMoveValid(IPitch.EXIT,sf,s1));
		assertTrue(ev.isExitMoveValid(1,sf,s1));
		sf.move(3, 0, s1);
		assertTrue(ev.isExitMoveValid(IPitch.EXIT,sf,s1));
	}
}
