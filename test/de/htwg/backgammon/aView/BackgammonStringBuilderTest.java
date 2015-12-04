package de.htwg.backgammon.aView;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.controller.GameState;
import de.htwg.backgammon.model.implementation.SpielFeld;
import de.htwg.backgammon.model.implementation.Spieler;
import de.htwg.backgammon.model.implementation.Stein;

public class BackgammonStringBuilderTest {

	final String result = "____________________________________\n 12 11 10  9  8  7  6  5  4  3  2  1\n  W           B     B              W\n  W           B     B              W\n  W           B     B               \n  W                 B               \n  W                 B               \n                                    \n  B                 W               \n  B                 W               \n  B           W     W               \n  B           W     W              B\n  B           W     W              B\n 13 14 15 16 17 18 19 20 21 22 23 24\n¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";

	final String scaledres = "______\n  2  1\n     W\n      \n      \n      \n      \n      \n      \n      \n      \n      \n     B\n  3  4\n¯¯¯¯¯¯\n";

	private final int number = 5;
	GameState gs, gsS;
	SpielFeld sf, sfS;
	int[] zuege = { number, 0, 0, 0 };
	Spieler player, black;

	@Before
	public void setUp() throws Exception {
		player = new Spieler("Jan", Stein.WHITE);
		black = new Spieler("Helga", Stein.BLACK);
		sf = new SpielFeld();
		sfS = new SpielFeld(1);
		gsS = new GameState(sfS, zuege, "no message", black, false);
		gs = new GameState(sf, zuege, "no message", player, false);
	}

	@Test
	public void testBackgammonStringBuilder() {
		
		String withX = "____________________________________\n 12 11 10  9  8  7  6  5  4  3  2  1\n  W           B     B              W\n  W           B     B               \n  W           B     B               \n  W                 B               \n  X                 B               \n                                    \n  X                 W               \n  B                 W               \n  B           W     W               \n  B           W     W               \n  B           W     W              B\n 13 14 15 16 17 18 19 20 21 22 23 24\n¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
	
		BackgammonStringBuilder bs = new BackgammonStringBuilder();
		StringBuilder sb = bs.getStringBuilder(gs);
		assertEquals(sb.toString(), result);
		sb = bs.getStringBuilder(gsS);
		System.out.println(sb.toString());
		assertEquals(sb.toString(), scaledres);
		sf.move(0, 11, player);
		sf.move(23, 12, black);
		gs = new GameState(sf, zuege, "no message", player, false);
		assertEquals(bs.getStringBuilder(gs).toString(),withX);
	}

	@Test
	public void testgetInformations() {
		String returnval = "Steine auf der Bar: 0\nWürfel: " + number + " \n";
		BackgammonStringBuilder bs = new BackgammonStringBuilder();
		StringBuilder sb = bs.getInformations(gs);
		assertEquals(sb.toString(), returnval);
		sb = bs.getInformations(gsS);
		assertEquals(sb.toString(), returnval);
	}

}
