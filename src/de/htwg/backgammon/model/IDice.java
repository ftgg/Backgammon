package de.htwg.backgammon.model;

public interface IDice {

	/**
	 * checks if both dice numbers are the same
	 * 
	 * @return true if both dice numbers are the same
	 */
	public boolean isDoublets();

	/**
	 * get an int array wich contains all diced cube numbers
	 * 
	 * @return current cube numbers
	 */
	public int[] getCurrentCubeNumbers();

	/**
	 * rolls the dices
	 * 
	 * @return int array with dice result
	 */
	public int[] rollTheDice();

	/**
	 * get maximum value of dice
	 * 
	 * @return max dice value
	 */
	public int getMax();

	/**
	 * get minimum value of dice
	 * 
	 * @return min dice value
	 */
	public int getMin();

}
