package de.htwg.backgammon.aview;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.aview.BackgammonStringBuilder;
import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.GameState;
import de.htwg.backgammon.model.implementation.Pitch;
import de.htwg.backgammon.model.implementation.Player;


public class BackgammonStringBuilderTest {

	final String result = "____________________________________\n 12 11 10  9  8  7  6  5  4  3  2  1\n  W           B     B              W\n  W           B     B              W\n  W           B     B               \n  W                 B               \n  W                 B               \n                                    \n  B                 W               \n  B                 W               \n  B           W     W               \n  B           W     W              B\n  B           W     W              B\n 13 14 15 16 17 18 19 20 21 22 23 24\nŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻ\n";

	final String scaledres = "______\n  2  1\n     W\n      \n      \n      \n      \n      \n      \n      \n      \n      \n     B\n  3  4\nŻŻŻŻŻŻ\n";

	private final int number = 5;
	GameState gs, gsS;
	Pitch sf, sfS;
	int[] zuege = { number, 0, 0, 0 };
	Player player, black;

	@Before
	public void setUp() throws Exception {
		player = new Player("Jan", TokenColor.WHITE);
		black = new Player("Helga", TokenColor.BLACK);
		sf = new Pitch();
		sfS = new Pitch(GameState.getTestGameState(1));
		gsS = new GameState(sfS, zuege, "no message", black, false,player,black);
		gs = new GameState(sf, zuege, "no message", player, false, player,black);
	}

	@Test
	public void testBackgammonStringBuilder() {
		
		String withX = "____________________________________\n 12 11 10  9  8  7  6  5  4  3  2  1\n  W           B     B              W\n  W           B     B               \n  W           B     B               \n  W                 B               \n  X                 B               \n                                    \n  X                 W               \n  B                 W               \n  B           W     W               \n  B           W     W               \n  B           W     W              B\n 13 14 15 16 17 18 19 20 21 22 23 24\nŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻŻ\n";
	
		BackgammonStringBuilder bs = new BackgammonStringBuilder();
		StringBuilder sb = bs.getStringBuilder(gs);
		assertEquals(sb.toString(), result);
		sb = bs.getStringBuilder(gsS);
		System.out.println(sb.toString());
		assertEquals(sb.toString(), scaledres);
		sf.move(0, 11, player);
		sf.move(23, 12, black);
		gs = new GameState(sf, zuege, "no message", player, false,player,black);
		assertEquals(bs.getStringBuilder(gs).toString(),withX);
	}

	@Test
	public void testgetInformations() {
		String returnval = "Steine auf der Bar: 0\nWürfel: " + number + " \nPlayer(WHITE) :Jan ist am Zug:";
		BackgammonStringBuilder bs = new BackgammonStringBuilder();
		StringBuilder sb = bs.getInformations(gs);
		assertEquals(sb.toString(), returnval);
		sb = bs.getInformations(gsS);
		assertNotEquals(sb.toString(), returnval);
	}

}
