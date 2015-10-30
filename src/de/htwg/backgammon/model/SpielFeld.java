package de.htwg.backgammon.model;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

/**
 * SpielFeld contains a List of all aviable Fields in which the tokens are.
 */

public class SpielFeld {

	private List<Dreieck> dreiecke;
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
		barone = new Dreieck();
		bartwo = new Dreieck();
		dreiecke = new LinkedList<Dreieck>();
		int quadsize;
		quadsize = size * 4;
		for (int i = 0; i < quadsize; i++) {
			dreiecke.add(new Dreieck());
		}
		Map<Integer, Integer> initpos = new TreeMap<>();

		initpos.put(0, 2);
		initpos.put(11, 5);
		initpos.put(16, 3);
		initpos.put(18, 5);
		if (quadsize == 24)
			fill(initpos);
	}

	/**
	 * fills the empty pitch
	 */
	private void fill(Map<Integer, Integer> initpos) {
		int color = Stein.WHITE;
		for (int invers = 0; invers < 2; invers++) {
			for (Map.Entry<Integer, Integer> current : initpos.entrySet()) {
				for (int i = 0; current.getValue() > i; i++) {
					dreiecke.get(Math.abs(current.getKey() - 23 * invers)).add(new Stein(color));
				}
			}
			color = Stein.BLACK;
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
		return dreiecke.get(i).isEmpty();
	}

	/**
	 * returns true if the Player has a empty Bar and can do a move on the field
	 * 
	 * @param spieler
	 *            playerid (Stone.White or Stone.Black)
	 * @return true if Bar is empty
	 */
	public boolean isBarEmpty(int spieler) {
		if (spieler == Stein.WHITE) {
			return barone.isEmpty();
		}
		return bartwo.isEmpty();
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
		if (!possibleMove(a, b, spieler))
			return -1;

		Stein attack = dreiecke.get(a).remove();
		Stein beaten = dreiecke.get(b).add(attack);

		if (beaten == null) { // target field was empty, nothing to do
			return 0;
		} else { // else the current move was an attack and we got a stone of
					// the enemy
			if (spieler == Stein.WHITE)
				bartwo.add(beaten); // white = player one, so the kicked token
									// has to be addet to bartwo
			else
				barone.add(beaten);
			return 1;
		}
	}

	public boolean possibleMove(int a, int b, int pc) {
		// there is a token of the current player in field a
		if (dreiecke.get(a).getColor() != pc)
			return false;
		// field b is attackable or own
		if (dreiecke.get(b).unsecure() || dreiecke.get(b).getColor() == pc)
			return true;
		return false;

	}

	public int countOfTriangles(int i) {
		return dreiecke.get(i).count();
	}
}
