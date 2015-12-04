package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;
import de.htwg.backgammon.model.Stein;

public class DirectionVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, SpielFeld sf, Spieler s, Controller c) {
		return isDirectionValid(a,b,c,sf);
	}
	
	public boolean isDirectionValid(int a, int b, Controller c, SpielFeld sf) {
		boolean color = (a - b < 0) && (c.getCurrent().getColor() == Stein.WHITE);
		return (a - b > 0) && c.getCurrent().getColor() == Stein.BLACK || color || b == sf.EXIT;
	}

}
