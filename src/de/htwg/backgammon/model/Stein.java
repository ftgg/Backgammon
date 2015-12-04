package de.htwg.backgammon.model;

public class Stein {
	
	private static final int BLACK = 0;
	private static final int WHITE = 1;
	
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

	public static int getBlack() {
		return BLACK;
	}

	public static int getWhite() {
		return WHITE;
	}
	
	
}
