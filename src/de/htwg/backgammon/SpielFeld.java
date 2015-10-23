package de.htwg.backgammon;

import java.util.LinkedList;

import javax.management.modelmbean.InvalidTargetObjectTypeException;

/**
 * SpielFeld contains a List of all aviable Fields in which the tokens are.
 */
public class SpielFeld {

	private LinkedList<Dreieck> dreiecke;
	private Dreieck barone; // Bar of Player one (White)
	private Dreieck bartwo; // Bar of Player two (Black)

	/**
	 * Creates a pitch with 24 Fields like the real Backgammon-Field
	 */
	public SpielFeld() {
		this(6);
	}

	/**
	 * creates a pitch witch 4 * Base size. Base size is the number of Fields in
	 * each quarter of the Field
	 * 
	 * @param size
	 *            the number of Fields in each Base
	 */
	public SpielFeld(int size) {
		dreiecke = new LinkedList<Dreieck>();
		size = size * 4;
		for (int i = 0; i < size; i++) {
			dreiecke.add(new Dreieck());
		}
	}

	/**
	 * returns the number of fields
	 * 
	 * @return the number of fields
	 */
	public int getSize() {
		return dreiecke.size();
	}

	/**
	 * Returns the Field on the position i
	 * 
	 * @param i
	 *            the index of the field
	 * @return the selected Field
	 */
	public Dreieck getDreiecke(int i) {
		return dreiecke.get(i);
	}

	private boolean isLegal(int i) {
		return false;
	}

	/**
	 * moves one token from a to b. only works if the move is legal, starts an
	 * attack if b is attackable or a move if b is empty or own
	 * 
	 * @param a
	 *            start position of move
	 * @param b
	 *            target position of move
	 * @return returns -1 if move is not possible, 0 if its a correct move and 1
	 *         if it is an attack
	 */
	public int zug(int a, int b) {
		if (isLegal(a))
			return -1;
		return 0;
	}

	/**
	 * puts a Stone out of a Field and puts it into the bar. the attacking token
	 * get put into the attacked Field.
	 * 
	 * @param target
	 *            the number of the field which should get attacked
	 * @param a
	 *            the token which attacks
	 */
	// private void angriff(int target,Stein a) {
	// Stein beaten;// = dreiecke.get(target).add(a)
	// if(beaten.getColor() == Stein.White){
	// //Player 1 got attacked
	// barone.add(beaten);
	//
	// }
	// }
}
