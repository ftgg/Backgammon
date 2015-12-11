package de.htwg.backgammon.model.implementation;

import de.htwg.backgammon.model.IToken;
import de.htwg.backgammon.model.TokenColor;

public class Token implements IToken {

	private TokenColor color;

	public Token(TokenColor color) {
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
