package de.htwg.backgammon.model.implementation;

import de.htwg.backgammon.model.TokenColor;

public class Token{

	private TokenColor color;

	public Token(TokenColor color) {
		if (color != TokenColor.BLACK && color != TokenColor.WHITE) {
			throw new IllegalArgumentException("Ungueltige Farbe");
		}
		this.color = color;
	}

	
	public TokenColor getColor() {
		return color;
	}

}
