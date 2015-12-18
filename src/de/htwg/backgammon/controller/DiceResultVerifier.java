package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;

import de.htwg.backgammon.model.TokenColor;

public class DiceResultVerifier extends MoveVerifier {
	
	@Override
	public boolean checkMove(int a, int b, int[] zuege, IPitch sf, IPlayer s, IPlayer s1, IPlayer s2) {
//		System.out.println(successor.checkMove(a, b, zuege, sf, s, s1, s2));
		return  successor.checkMove(a, b, zuege, sf, s, s1, s2) && inDiceResult(a,b,zuege,sf,s);
	}
	
	
	public boolean inDiceResult(int a, int b, int[] zuege,IPitch sf, IPlayer s){
		int value = getDistance(a,b, sf, s);
		int max = 0;
		boolean indiceResult = true;
		for (int i : zuege) {
			indiceResult = (value != i && indiceResult);
			max = Math.max(max, i);
		}
		
		if(!indiceResult && a != IPitch.BAR){
			removeThrow(value,zuege);
			return true;
		}else if(max >= value && b == IPitch.EXIT){
			removeThrow(max,zuege);
			return true;
		}else if(!indiceResult && a == IPitch.BAR){
			removeThrow(value ,zuege);
			return true;
		}	
		
		return false;
	}

	public int getDistance(int a, int b, IPitch sf, IPlayer current) {
		int start = sf.getSize();
		int end = -1;
		if (current.getColor() == TokenColor.WHITE) {
			start = -1;
			end = sf.getSize();
		}
		
		if (a == IPitch.BAR)
			a = start;
		else if (b == IPitch.EXIT)
			b = end;
		return Math.abs(b - a);
	}

	/**
	 * deleates the current move from zuege
	 */
	protected void removeThrow(int a, int[] zuege) {
		for (int i = 0; i < 4; i++)
			if (zuege[i] == a) {
				zuege[i] = 0;
				break;
			}
	}
	
	
}
