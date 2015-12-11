package de.htwg.backgammon.model;

import de.htwg.backgammon.model.IToken;

public interface ITriangle {

	/**
	 * add a Token to this triangle
	 * 
	 * @param token
	 *            token
	 * @return null if its just a move or a token if an enemy has been slain
	 */
	public IToken add(IToken token);

	/**
	 * return false if there are more than one enemy tokens on the field
	 * @return true if field is assignable
	 */
	public boolean unsecure();

	/**
	 * removes a token from the triangle
	 * @return removed token
	 */
	public IToken remove();

	/**
	 * count number of tokens on triangle
	 * @return ammount of tokens
	 */
	public int count();

	/**
	 * return the tokens color
	 * @return color of token
	 */
	public TokenColor getColor();

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
