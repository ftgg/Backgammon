package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.Player;

public class TargetColorVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, Pitch sf, Player s, Player s1, Player s2) {
		return isTargetColorValid(a, b, s, sf) && successor.checkMove(a, b, zuege, sf, s, s1, s2);
	}

	public boolean isTargetColorValid(int a, int b, Player s, Pitch sf) {
		// there is a token of the current player in field a
		if (a != Pitch.BAR && sf.getTriangle(a).getColor() != s.getColor())
			return false;
		// field b is attackable or own
		return (b == Pitch.EXIT || sf.getTriangle(b).unsecure() || sf.getTriangle(b).getColor() == s.getColor());
	}

}
