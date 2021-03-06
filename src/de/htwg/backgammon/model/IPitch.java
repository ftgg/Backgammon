package de.htwg.backgammon.model;

public interface IPitch {
	public static final int EXIT = -1;
	public static final int BAR = -2;

	/**
	 * return size of pitch
	 * 
	 * @return number of triangles on pitch
	 */
	public int getSize();

	public TokenColor getTriangleColor(int i);
	
	public boolean getTriangleUnsecure(int i);

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
	public boolean isBarEmpty(IPlayer player);

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
	public int move(int start, int destination, IPlayer player);

	/**
	 * return true if the index is element of current players home indices
	 * 
	 * @param i
	 *            index
	 * @param current
	 *            current player
	 * @return true if index is legal
	 */
	public boolean indexInHome(int i, IPlayer current);

	/**
	 * returns true if current player has all tokens in home indices, so player
	 * can now remove tokens
	 * 
	 * @param current
	 *            current player
	 * @return true if all tokens are home
	 */
	public boolean allHome(IPlayer current);

}
