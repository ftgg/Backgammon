package de.htwg.backgammon.model.implementation;

import de.htwg.backgammon.model.Token;

public class Stein implements Token {

	private int color;

	public Stein(int color) {
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
