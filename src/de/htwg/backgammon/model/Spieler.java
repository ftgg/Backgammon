package de.htwg.backgammon.model;

public class Spieler {

	private String name;
	private int color;

	/**
	 * Player construktor set playername and player color
	 * 
	 * @param name
	 *            name of this player
	 * @param color
	 *            color of the player, has to be Stein.White or Stein.Black!
	 */
	public Spieler(String name, int color) {
		// color has to be Stein,White or Stein.Black!
		if (color == Stein.White)
			this.color = Stein.White;
		else
			this.color = Stein.Black;
		this.name = name;

	}

	public int getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

}
