package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;
import de.htwg.backgammon.model.Stein;

public class DiceResultVerifier extends MoveVerifier {
	
	@Override
	public boolean checkMove(int a, int b, int[] zuege, SpielFeld sf, Spieler s, Spieler s1, Spieler s2) {
		return inDiceResult(a,b,zuege,sf,s) && successor.checkMove(a, b, zuege, sf, s, s1, s2);
	}
	
	public boolean inDiceResult(int a, int b, int[] zuege,SpielFeld sf, Spieler s){
		int value = getDistance(a,b, sf, s);
		int max = 0;
		boolean indiceResult = true;
		for (int i : zuege) {
			indiceResult = (value != i && indiceResult);
			max = Math.max(max, i);
		}
		if (b == SpielFeld.EXIT)
			return max >= value;
		return !indiceResult;
	}

	public int getDistance(int a, int b, SpielFeld sf, Spieler current) {
		int start = sf.getSize();
		int end = -1;
		if (current.getColor() == Stein.WHITE) {
			start = 0;
			end = sf.getSize();
		}
		if (a == sf.BAR)
			a = start;
		else if (b == sf.EXIT)
			b = end;
		return Math.abs(b - a);
	}

	
	
	
}
