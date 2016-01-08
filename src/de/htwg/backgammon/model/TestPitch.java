package de.htwg.backgammon.model;

import de.htwg.backgammon.model.implementation.Triangle;

/**
 * approved exclusively for use of tests
 * 
 * @author thgnaedi
 */
public interface TestPitch extends IPitch {

	/**
	 * returns number of tokens on triangle
	 */
	public int countOfTriangle(int i);
	
	/**
	 * returns array with all tokens
	 */
	public int[] getTokensOnTriangle();
	
}
