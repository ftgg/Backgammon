package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.Player;
import de.htwg.backgammon.model.TokenColor;


public class DirectionVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, Pitch sf, Player s, Player s1, Player s2) {
		System.out.println("Direction Verifier: "+ isDirectionValid(a,b,s));
		return isDirectionValid(a,b,s);
	}
	
	public boolean isDirectionValid(int a, int b, Player c) {
		boolean white = (a - b < 0) && (c.getColor() == TokenColor.WHITE);
		boolean black = (a - b > 0) && c.getColor() == TokenColor.BLACK;
		return black || white || b == Pitch.EXIT || a == Pitch.BAR;
	}

}
