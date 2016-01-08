package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;

public class TargetColorVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, IPitch sf, IPlayer s, IPlayer s1, IPlayer s2) {
//		System.out.println("TargetColor: " + (isTargetColorValid(a, b, s, sf)));
		return isTargetColorValid(a, b, s, sf) && successor.checkMove(a, b, zuege, sf, s, s1, s2);
	}

	public boolean isTargetColorValid(int a, int b, IPlayer s, IPitch sf) {
		// there is a token of the current player in field a
		if (a != IPitch.BAR && sf.getTriangleColor(a)!= s.getColor())
			return false;
		// field b is attackable or own
		return (b == IPitch.EXIT || sf.getTriangleUnsecure(b) || sf.getTriangleColor(b) == s.getColor());
	}

}
