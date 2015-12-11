package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.Player;

public class ExitMoveVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, Pitch sf, Player s, Player s1, Player s2) {
		System.out.println("Exit Verifier: "+ isExitMoveValid(b, sf, s));
		return isExitMoveValid(b, sf, s) && successor.checkMove(a, b, zuege, sf, s, s1, s2);
	}

	public boolean isExitMoveValid(int b, Pitch sf, Player c) {
		return !(b == Pitch.EXIT) || sf.allHome(c); // Implikation
	}

}
