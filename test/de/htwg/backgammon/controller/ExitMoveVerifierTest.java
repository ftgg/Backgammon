package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;
import de.htwg.backgammon.model.Stein;

public class ExitMoveVerifierTest {

	ExitMoveVerifier ev;
	SpielFeld sf;
	Spieler s1;
	@Before
	public void setUp() throws Exception {
		ev = new ExitMoveVerifier();
		sf = new SpielFeld(1);
		s1 = new Spieler("b", Stein.BLACK);
	}

	@Test
	public void testisExitMoveValid(){	
		assertFalse(ev.isExitMoveValid(Pitch.EXIT,sf,s1));
		assertTrue(ev.isExitMoveValid(1,sf,s1));
		sf.move(3, 0, s1);
		assertTrue(ev.isExitMoveValid(Pitch.EXIT,sf,s1));
	}
}
