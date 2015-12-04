package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;

public class ExitMoveVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, SpielFeld sf, Spieler s, Spieler s1, Spieler s2) {
		return isExitMoveValid(b, sf, s) && successor.checkMove(a, b, zuege, sf, s, s1, s2);
	}

	public boolean isExitMoveValid(int b, SpielFeld sf, Spieler c) {
		return !(b == sf.EXIT) || sf.allHome(c); // Implikation
	}

}
