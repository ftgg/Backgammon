package de.htwg.backgammon.model;

import de.htwg.backgammon.model.Triangle;

public interface Pitch {
	public static final int EXIT = -1;
	public static final int BAR = -2;

	/**
	 * return size of pitch
	 * 
	 * @return number of triangles on pitch
	 */
	public int getSize();

	/**
	 * return the selected triangle (on index i)
	 * 
	 * @param i
	 *            index
	 * @return triangle on index i
	 */
	public Triangle getTriangle(int i);

	/**
	 * returns true if there is no token on the selected triangle
	 * 
	 * @param i
	 *            triangle index
	 * @return true if triangle is empty
	 */
	public boolean isEmpty(int i);

	/**
	 * returns true if there is no token on the current players bar
	 * 
	 * @param player
	 *            current player
	 * @return true if player has no tokens on his bar
	 */
	public boolean isBarEmpty(Player player);

	/**
	 * returns the number of tokens on players bar, 0 if isBarEmpty()
	 * 
	 * @param player
	 *            current player
	 * @return number of tokens on players bar
	 */
	public int getBarCount(TokenColor spieler);

	/**
	 * tries to move one token from start to destination
	 * 
	 * @param start
	 *            index of start position, can be negative if start is bar
	 * @param destination
	 *            index of destination position, can be negative if destination
	 *            is bar or exit
	 * @param player
	 *            current player
	 * @return 0 if its a move, 1 if an enemy has been slain
	 */
	public int move(int start, int destination, Player player);

	/**
	 * return true if the index is element of current players home indices
	 * 
	 * @param i
	 *            index
	 * @param current
	 *            current player
	 * @return true if index is legal
	 */
	public boolean indexInHome(int i, Player current);

	/**
	 * returns true if current player has all tokens in home indices, so player
	 * can now remove tokens
	 * 
	 * @param current
	 *            current player
	 * @return true if all tokens are home
	 */
	public boolean allHome(Player current);

	/**
	 * @return bar
	 */
	public Triangle getBarblack();

	public Triangle getBarwhite();
}
