package de.htwg.backgammon.model.implementation;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IToken;
import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.ITriangle;

public abstract class AbstractMove {

	Pitch sf;
	int a;
	int b;
	Player s;
	ITriangle currentbar;
	ITriangle otherbar;
	
	public static AbstractMove createMoveObject(int a, int b, Player s, Pitch sf, int[] stonesOnField){
		if(a == IPitch.BAR)
			return new BarMove(a,b,s,sf);
		if(b == IPitch.EXIT)
			return new ExitMove(a,b,s,sf,stonesOnField);
		return new StandardMove(a,b,s,sf);
	}
	
	
	protected AbstractMove(int a, int b, Player s, Pitch sf){
		this.a = a;
		this.b = b;
		this.s = s;
		this.sf = sf;
		
		if (s.getColor() == TokenColor.WHITE){
			currentbar = sf.getBarwhite();
			otherbar = sf.getBarblack();
		}else{
			currentbar = sf.getBarblack();
			otherbar = sf.getBarwhite();
		}
	}


	public abstract int move();
	

	protected int putOnBar(IToken beaten){
		if (beaten == null) // target field was empty
			return 0;
		otherbar.add(beaten);
		return 1;
	}
	
}
