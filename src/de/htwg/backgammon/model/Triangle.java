package de.htwg.backgammon.model;

public interface Triangle {

	/**
	 * add a Token to this triangle
	 * 
	 * @param token
	 *            token
	 * @return null if its just a move or a token if an enemy has been slain
	 */
	public Stein add(Stein token);

	/**
	 * return false if there are more than one enemy tokens on the field
	 * @return true if field is assignable
	 */
	public boolean unsecure();

	/**
	 * removes a token from the triangle
	 * @return removed token
	 */
	public Stein remove();

	/**
	 * count number of tokens on triangle
	 * @return ammount of tokens
	 */
	public int count();

	/**
	 * return the tokens color
	 * @return color of token
	 */
	public int getColor();

	/**
	 * true if count() is 0
	 * @return true if triangle is empty
	 */
	public boolean isEmpty();

	/**
	 * clears triangle
	 */
	public void clear();

}
