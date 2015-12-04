package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.Player;
import de.htwg.backgammon.model.implementation.Stein;

public class DirectionVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, Pitch sf, Player s, Player s1, Player s2) {
		return isDirectionValid(a,b,s);
	}
	
	public boolean isDirectionValid(int a, int b, Player c) {
		boolean color = (a - b < 0) && (c.getColor() == Stein.WHITE);
		return (a - b > 0) && c.getColor() == Stein.BLACK || color || b == Pitch.EXIT;
	}

}
