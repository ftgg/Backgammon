package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;

public class BarVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, IPitch sf, IPlayer c, IPlayer s1, IPlayer s2) {
		return checkBarmove(a,b,sf,c,s1,s2) && successor.checkMove(a, b, zuege, sf, c, s1,s2);
	}
	
	boolean checkBarmove(int a, int b, IPitch sf, IPlayer c, IPlayer s1, IPlayer s2){
		boolean isBarEmpty = sf.isBarEmpty(c);
		boolean aisbar = a == IPitch.BAR;
		boolean indexInHome = sf.indexInHome(b, otherPlayer(c,s1,s2));
		return (isBarEmpty && !aisbar || aisbar && indexInHome && !isBarEmpty);
	}
	
	IPlayer otherPlayer(IPlayer c, IPlayer s1, IPlayer s2) {
		return c == s1 ? s2 : s1;
	}
}
