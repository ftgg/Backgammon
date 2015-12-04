package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.Spieler;

public class BarVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, Pitch sf, Spieler c, Spieler s1, Spieler s2) {
		return checkBarmove(a,b,sf,c,s1,s2) && successor.checkMove(a, b, zuege, sf, c, s1,s2);
	}
	
	boolean checkBarmove(int a, int b, Pitch sf, Spieler c, Spieler s1, Spieler s2){
		boolean isBarEmpty = sf.isBarEmpty(c);
		boolean aisbar = a == Pitch.BAR;
		boolean indexInHome = sf.indexInHome(b, otherPlayer(c,s1,s2));
		return (isBarEmpty && !aisbar || aisbar && indexInHome);
	}
	
	Spieler otherPlayer(Spieler c, Spieler s1, Spieler s2) {
		return c == s1 ? s2 : s1;
	}
}
