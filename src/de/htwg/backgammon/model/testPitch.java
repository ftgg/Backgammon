package de.htwg.backgammon.model;

import de.htwg.backgammon.model.implementation.Dreieck;

/**
 * approved exclusively for use of tests
 * 
 * @author thgnaedi
 */
public interface testPitch extends Pitch {

	/**
	 * returns number of tokens on triangle
	 */
	public int countOfTriangle(int i);
	
	/**
	 * returns array with all tokens
	 */
	public int[] getTokensOnTriangle();
	

}
