package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;

public class IndexVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, IPitch sf, IPlayer s, IPlayer s1, IPlayer s2) {
		return isIndexValid(a,b,sf) && successor.checkMove(a, b, zuege, sf, s, s1, s2);
	}
	
	boolean isIndexValid(int a, int b, IPitch sf){
		return aIsValid(a,sf) && bIsValid(b, sf);
	}
	
	boolean aIsBar(int a){
		return a == IPitch.BAR;
	}
	
	boolean aInRange(int a, IPitch sf){
		return a < sf.getSize() && a >= 0;
	}
	
	boolean bIsExit(int b){
		return b == IPitch.EXIT;
	}
	
	boolean bInRange(int b, IPitch sf){
		return b >=0 && b < sf.getSize();
	}
	
	boolean aIsValid(int a, IPitch sf){
		return aInRange(a,sf) || aIsBar(a);
	}
	
	boolean bIsValid(int b, IPitch sf){
		return bInRange(b,sf) ||  bIsExit(b);
	}

}
