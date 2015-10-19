package de.htwg.backgammon;

public class Stein {
	
	public static final int Black = 0;
	public static final int White = 1;
	
	public static final int onField = 0;
	public static final int Suspended = 1;
	public static final int atHome = 2;
	
	private int Color;
	private int state;
	
	
	Stein (int color){
		if(color != Black && color != White){
			throw new IllegalArgumentException("Ungueltige Farbe");
		}
		this.Color = color;
		state = 0;
	}
	
	public int getColor(){
		return Color;
	}
	
	public int getState(){
		return state;
	}
	
	public void setState(int state){
		if(state != onField && state != Suspended && state != atHome){
			throw new IllegalArgumentException("Ungueltiger State");
		}
		this.state = state;
	}
	
}
