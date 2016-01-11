package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;

import de.htwg.backgammon.model.TokenColor;

public class DiceResultVerifier extends MoveVerifier {
	
	@Override
	public boolean checkMove(int a, int b, int[] zuege, IPitch sf, IPlayer s, IPlayer s1, IPlayer s2) {
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
		
		if(noneBar(a, indiceResult)){
			removeThrow(value,zuege);
			return true;
		}else if(isExit(max, value, b)){
			removeThrow(max,zuege);
			return true;
		}else if(isBar(a, indiceResult)){
			removeThrow(value ,zuege);
			return true;
		}	
		
		return false;
	}
	
	private boolean isBar(int a, boolean indiceResult){
		return !indiceResult && a == IPitch.BAR;
	}
	
	private boolean isExit(int max,int value,int b){
		return max >= value && b == IPitch.EXIT;
	}
	
	private boolean noneBar(int a, boolean indiceResult){
		return !indiceResult && a != IPitch.BAR;
	}
	

	public int getDistance(int a_, int b_, IPitch sf, IPlayer current) {
		int start = sf.getSize();
		int a = a_;
		int b = b_;
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
	
	
	protected void removeThrow(int a, int[] zuege) {
		for (int i = 0; i < 4; i++)
			if (zuege[i] == a) {
				zuege[i] = 0;
				break;
			}
	}
	
	
}
