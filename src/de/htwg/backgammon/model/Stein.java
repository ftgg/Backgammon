package de.htwg.backgammon.model;

public class Stein {
	
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	
	private int color;

	
	
	Stein (int color){
		if(color != BLACK && color != WHITE){
			throw new IllegalArgumentException("Ungueltige Farbe");
		}
		this.color = color;
	}
	
	public int getColor(){
		return color;
	}
	
}
