package de.htwg.backgammon;


import java.util.LinkedList;


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

	/**
	 * Returns true if there is at least one token on the field
	 * 
	 * @param i
	 *            index of the Field
	 * @return true if there is at least one token on this field, else false
	 */
	public boolean isEmpty(int i) {
//		return dreiecke.get(i).isEmpty();
		return false;
	}

	/**
	 * returns true if the Player has a empty Bar and can do a move on the field
	 * 
	 * @param spieler
	 *            playerid (Stone.White or Stone.Black)
	 * @return true if Bar is empty
	 */
	public boolean isBarEmpty(int spieler) {
//		if (spieler == Stein.White) {
//			return barone.isEmpty();
//		}
//		return bartwo.isEmpty();
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
	 * @param spieler
	 *            spieler can be Stein.Black or Stein.White
	 * @return returns -1 if move is not possible, 0 if its a correct move and 1
	 *         if it is an attack
	 */
	 public int zug(int a, int b, int spieler) {
//	 //there is a token of the current player
//	 if (dreiecke.get(a).getColour() == spieler)
//	 Stein attacker = dreiecke.get(b).remove;
//	 Stein beaten = dreiecke.get(b).add(attacker);
//	 //target field was empty, nothing to do
//	 if(beaten == null)
//	 return 0;
//	 //else the current move was an attack and we got a stone of the enemy
//	 else{
//	 //white = player one, so the kicked token has to be addet to bartwo
//	 if(spieler == Stein.White)
//	 bartwo.add(beaten);
//	 else
//	 barone.add(beaten);
//	 }
//	 //else, there is no token of the current player, so return move not
//	 possible
	 return -1;
	 }

}
