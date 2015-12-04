package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.Player;
import de.htwg.backgammon.model.Token;

public class DiceResultVerifier extends MoveVerifier {
	
	@Override
	public boolean checkMove(int a, int b, int[] zuege, Pitch sf, Player s, Player s1, Player s2) {
		return inDiceResult(a,b,zuege,sf,s) && successor.checkMove(a, b, zuege, sf, s, s1, s2);
	}
	
	public boolean inDiceResult(int a, int b, int[] zuege,Pitch sf, Player s){
		int value = getDistance(a,b, sf, s);
		int max = 0;
		boolean indiceResult = true;
		for (int i : zuege) {
			indiceResult = (value != i && indiceResult);
			max = Math.max(max, i);
		}
		return !indiceResult || (max >= value && b == Pitch.EXIT);
	}

	public int getDistance(int a, int b, Pitch sf, Player current) {
		int start = sf.getSize();
		int end = -1;
		if (current.getColor() == Token.WHITE) {
			start = 0;
			end = sf.getSize();
		}
		if (a == Pitch.BAR)
			a = start;
		else if (b == Pitch.EXIT)
			b = end;
		return Math.abs(b - a);
	}

	
	
	
}
