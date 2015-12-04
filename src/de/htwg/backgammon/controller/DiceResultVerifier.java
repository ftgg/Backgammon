package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;
import de.htwg.backgammon.model.Stein;

public class DiceResultVerifier extends MoveVerifier {
	
	@Override
	public boolean checkMove(int a, int b, int[] zuege, SpielFeld sf, Spieler s, Controller c) {
		return inDiceresult(a,b,zuege,sf,s) && successor.checkMove(a, b, zuege, sf, s,c);
	}
	
	public boolean inDiceresult(int a, int b, int[] zuege,SpielFeld sf, Spieler s){
		int value = getDistance(a,b,zuege, sf, s);
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

	public int getDistance(int a, int b, int[] zuege, SpielFeld sf, Spieler current) {
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
