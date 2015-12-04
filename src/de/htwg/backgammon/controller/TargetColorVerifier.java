package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;

public class TargetColorVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, SpielFeld sf, Spieler s, Controller c) {
		
		return isTargetColorValid(a,b,s,sf) && successor.checkMove(a, b, zuege, sf, s, c);
	}
	
	public boolean isTargetColorValid(int a, int b, Spieler s,SpielFeld sf) {
		// there is a token of the current player in field a
		if (a != sf.BAR && sf.getDreieck(a).getColor() != s.getColor())
			return false;
		// field b is attackable or own
		return (b == sf.EXIT || sf.getDreieck(b).unsecure() || sf.getDreieck(b).getColor() == s.getColor());
	}

}
