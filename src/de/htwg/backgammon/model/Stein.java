package de.htwg.backgammon.model;

public class Stein implements Token {

	private int color;

	Stein(int color) {
		if (color != BLACK && color != WHITE) {
			throw new IllegalArgumentException("Ungueltige Farbe");
		}
		this.color = color;
	}

	@Override
	public int getColor() {
		return color;
	}

}
