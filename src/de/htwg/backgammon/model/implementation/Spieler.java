package de.htwg.backgammon.model.implementation;

import de.htwg.backgammon.model.Player;
import de.htwg.backgammon.model.TokenColor;

public class Spieler implements Player {

	private String name;
	private TokenColor color;

	/**
	 * Player construktor set playername and player color
	 * 
	 * @param name
	 *            name of this player
	 * @param color
	 *            color of the player, has to be Stein.White or Stein.Black!
	 */
	public Spieler(String name, TokenColor color) {
		// color has to be Stein,White or Stein.Black!
		if (color == TokenColor.WHITE)
			this.color = TokenColor.WHITE;
		else
			this.color = TokenColor.BLACK;
		this.name = name;

	}

	@Override
	public TokenColor getColor() {
		return color;
	}

	@Override
	public String getName() {
		return name;
	}

}
