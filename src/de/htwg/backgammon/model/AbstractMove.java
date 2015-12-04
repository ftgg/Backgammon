package de.htwg.backgammon.model;

public abstract class AbstractMove {

	protected SpielFeld sf;
	protected int a;
	protected int b;
	protected Spieler s;
	protected Dreieck currentbar;
	private Dreieck otherbar;
	
	public static AbstractMove createMoveObject(int a, int b, Spieler s, SpielFeld sf, int[] stonesOnField){
		if(a == sf.BAR)
			return new BarMove(a,b,s,sf);
		if(b == sf.EXIT)
			return new ExitMove(a,b,s,sf,stonesOnField);
		return new StandardMove(a,b,s,sf);
	}
	
	
	protected AbstractMove(int a, int b, Spieler s, SpielFeld sf){
		this.a = a;
		this.b = b;
		this.s = s;
		this.sf = sf;
		
		if (s.getColor() == Stein.WHITE){
			currentbar = sf.getBarwhite();
			otherbar = sf.getBarblack();
		}else{
			currentbar = sf.getBarblack();
			otherbar = sf.getBarwhite();
		}
	}
	

	public abstract int move();
	

	protected int putOnBar(Stein beaten){
		if (beaten == null) // target field was empty
			return 0;
		otherbar.add(beaten);
		return 1;
	}
	
}
