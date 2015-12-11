package de.htwg.backgammon.model.implementation;

import de.htwg.backgammon.model.Token;
import de.htwg.backgammon.model.TokenColor;

public class Stein implements Token {

	private TokenColor color;

	public Stein(TokenColor color) {
		if (color != TokenColor.BLACK && color != TokenColor.WHITE) {
			throw new IllegalArgumentException("Ungueltige Farbe");
		}
		this.color = color;
	}

	@Override
	public TokenColor getColor() {
		return color;
	}

}
