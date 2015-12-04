package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;

public class BarVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, SpielFeld sf, Spieler s, Controller c) {
		boolean isBarEmpty = sf.isBarEmpty(s);
		boolean aisbar = a == sf.BAR;
		boolean indexInHome = sf.indexInHome(b, c.otherPlayer(s));
		return (isBarEmpty || aisbar && indexInHome) && successor.checkMove(a, b, zuege, sf, s, c);
	}
	

}
