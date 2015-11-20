package de.htwg.backgammon.aView;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.controller.GameState;
import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;
import de.htwg.backgammon.model.Stein;

public class BackgammonStringBuilderTest {

	final String result = "____________________________________\n 12 11 10  9  8  7  6  5  4  3  2  1\n  W           B     B              W\n  W           B     B              W\n  W           B     B               \n  W                 B               \n  W                 B               \n                                    \n  B                 W               \n  B                 W               \n  B           W     W               \n  B           W     W              B\n  B           W     W              B\n 13 14 15 16 17 18 19 20 21 22 23 24\n¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
	
	final String scaledres = "______\n  2  1\n     W\n      \n      \n      \n      \n      \n      \n      \n      \n      \n     B\n  3  4\n¯¯¯¯¯¯\n";
	
	
	GameState gs, gsS;
	SpielFeld sf, sfS;
	int[] zuege = { 2, 0, 0, 0 };
	Spieler player;

	@Before
	public void setUp() throws Exception {
		player = new Spieler("Jan", Stein.WHITE);
		sf = new SpielFeld();
		sfS = new SpielFeld(1);
		gsS = new GameState(sfS, zuege, "no message", player);
		gs = new GameState(sf, zuege, "no message", player);
	}

	@Test
	public void testBackgammonStringBuilder() {
		BackgammonStringBuilder bs = new BackgammonStringBuilder();
		StringBuilder sb = bs.getStringBuilder(gs);
		assertEquals(sb.toString(), result);
		sb = bs.getStringBuilder(gsS);
		assertEquals(sb.toString(), scaledres);

	}

}
