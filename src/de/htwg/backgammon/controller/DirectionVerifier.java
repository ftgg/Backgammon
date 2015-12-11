package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;
import de.htwg.backgammon.model.TokenColor;


public class DirectionVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, IPitch sf, IPlayer s, IPlayer s1, IPlayer s2) {
		System.out.println("Direction Verifier: "+ isDirectionValid(a,b,s));
		return isDirectionValid(a,b,s);
	}
	
	public boolean isDirectionValid(int a, int b, IPlayer c) {
		boolean white = (a - b < 0) && (c.getColor() == TokenColor.WHITE);
		boolean black = (a - b > 0) && c.getColor() == TokenColor.BLACK;
		return black || white || b == IPitch.EXIT || a == IPitch.BAR;
	}

}
