package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;

public class IndexVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, IPitch sf, IPlayer s, IPlayer s1, IPlayer s2) {
		return isIndexValid(a,b,sf) && successor.checkMove(a, b, zuege, sf, s, s1, s2);
	}
	
	private boolean isIndexValid(int a, int b, IPitch sf){
		return aIsValid(a,sf) && bIsValid(b, sf);
	}
	
	private boolean aIsBar(int a){
		return a == IPitch.BAR;
	}
	
	private boolean aInRange(int a, IPitch sf){
		return a < sf.getSize() && a >= 0;
	}
	
	private boolean bIsExit(int b){
		return b == IPitch.EXIT;
	}
	
	private boolean bInRange(int b, IPitch sf){
		return b >=0 && b < sf.getSize();
	}
	
	private boolean aIsValid(int a, IPitch sf){
		return aInRange(a,sf) || aIsBar(a);
	}
	
	private boolean bIsValid(int b, IPitch sf){
		return bInRange(b,sf) ||  bIsExit(b);
	}

}
