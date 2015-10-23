package de.htwg.backgammon.model;

public class Stein {
	
	public static final int Black = 0;
	public static final int White = 1;
	
	private int Color;

	
	
	Stein (int color){
		if(color != Black && color != White){
			throw new IllegalArgumentException("Ungueltige Farbe");
		}
		this.Color = color;
	}
	
	public int getColor(){
		return Color;
	}
	
}
