package de.htwg.backgammon.model;

/**
 * approved exclusively for use of tests
 * 
 * @author thgnaedi
 */
public interface TestPitch extends Pitch {

	/**
	 * returns number of tokens on triangle
	 */
	public int countOfTriangle(int i);
	
	/**
	 * returns array with all tokens
	 */
	public int[] getTokensOnTriangle();
	

}
