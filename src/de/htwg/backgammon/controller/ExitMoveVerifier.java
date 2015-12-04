package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;

public class ExitMoveVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, SpielFeld sf, Spieler s, Controller c) {
		return isExitMoveValid(b,sf,c) && successor.checkMove(a, b, zuege, sf, s, c);
	}
	public boolean isExitMoveValid(int b,SpielFeld sf, Controller c) {
		return !(b == sf.EXIT) || sf.allHome(c.getCurrent()); // Implikation
	}

}
