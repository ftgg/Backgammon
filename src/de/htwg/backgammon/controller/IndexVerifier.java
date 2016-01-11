package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;

public class IndexVerifier extends MoveVerifier {

	@Override
	public boolean checkMove(int a, int b, int[] zuege, IPitch sf, IPlayer s, IPlayer s1, IPlayer s2) {
		return (a >= 0 || a == IPitch.BAR || a == IPitch.EXIT) && 
			   (b >=0 || b == IPitch.BAR || b == IPitch.EXIT) && 
			   a < sf.getSize() && b < sf.getSize() && 
			   successor.checkMove(a, b, zuege, sf, s, s1, s2);
	}

}
