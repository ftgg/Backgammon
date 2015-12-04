package de.htwg.backgammon.model;

public interface Token {

	/**
	 * color black
	 */
	public static final int BLACK = 0;
	/**
	 * color white
	 */
	public static final int WHITE = 1;

	/**
	 * to get color of current token
	 * 
	 * @return color of this token
	 */
	public int getColor();
}
