package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;

public class ExitMoveVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, IPitch sf, IPlayer s, IPlayer s1, IPlayer s2) {
//		System.out.println("Exit Verifier: "+ isExitMoveValid(b, sf, s));
		return isExitMoveValid(b, sf, s) && successor.checkMove(a, b, zuege, sf, s, s1, s2);
	}

	public boolean isExitMoveValid(int b, IPitch sf, IPlayer c) {
		return !(b == IPitch.EXIT) || sf.allHome(c); // Implikation
	}

}
