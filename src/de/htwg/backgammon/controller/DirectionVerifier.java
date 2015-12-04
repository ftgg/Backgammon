package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;
import de.htwg.backgammon.model.Stein;

public class DirectionVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, SpielFeld sf, Spieler s, Spieler s1, Spieler s2) {
		return isDirectionValid(a,b,s);
	}
	
	public boolean isDirectionValid(int a, int b, Spieler c) {
		boolean color = (a - b < 0) && (c.getColor() == Stein.WHITE);
		return (a - b > 0) && c.getColor() == Stein.BLACK || color || b == SpielFeld.EXIT;
	}

}
