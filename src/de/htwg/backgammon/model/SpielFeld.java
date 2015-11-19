package de.htwg.backgammon.model;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

/**
 * SpielFeld contains a List of all aviable Fields in which the tokens are.
 */

public class SpielFeld {

	public static final int EXIT = -1;
	public static final int BAR = -2;
	private List<Dreieck> dreiecke;
	private Dreieck barblack; // Bar of Player one (White)
	private Dreieck barwhite; // Bar of Player two (Black)

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
		barblack = new Dreieck();
		barwhite = new Dreieck();
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
	public boolean isBarEmpty(Spieler spieler) {
		if (spieler.getColor() == Stein.WHITE) {
			return barblack.isEmpty();
		}
		return barwhite.isEmpty();
	}

	public int getBarCount(int spieler) {
		if (spieler == Stein.WHITE) {
			return barblack.count();
		}
		return barwhite.count();
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
	 * @return returns 0 if its a correct move and 1 if it is an attack
	 */
	public int zug(int a, int b, Spieler s) {
		assert isMovePossible(a, b, s);

		Stein attack = dreiecke.get(a).remove();
		Stein beaten = dreiecke.get(b).add(attack);

		if (beaten == null) // target field was empty, nothing to do
			return 0;
		// else the current move was an attack and we got a stone of
		// the enemy
		if (s.getColor() == Stein.WHITE)
			barwhite.add(beaten);
		else
			barblack.add(beaten);
		return 1;
	}

	public boolean isMovePossible(int a, int b, Spieler s) {
		// there is a token of the current player in field a
		if(a != BAR)
			if (dreiecke.get(a).getColor() != s.getColor())
				return false;
		// field b is attackable or own
		return (dreiecke.get(b).unsecure() || dreiecke.get(b).getColor() == s.getColor());
	}

	public int countOfTriangles(int i) {
		return dreiecke.get(i).count();
	}

	public boolean indexInHome(int b, Spieler current) {
		int quarterRange = getSize() / 4;
		if (current.getColor() == Stein.BLACK) {
			return valueInRange(b, 0, quarterRange - 1);
		} else {
			// spieler schwarz
			return valueInRange(b, quarterRange * 3, quarterRange * 4 - 1);
		}
	}

	private boolean valueInRange(int value, int min, int max) {
		// min und max angabe für noch in der base
		if (value > max || value < min) {
			return false;
		}
		return true;
	}

	/**
	 * are all tokens of the player in the last quarter of the pitch
	 * 
	 * @param current
	 *            player
	 * @return
	 */
	public boolean allHome(Spieler current) {
		// TODO Auto-generated method stub
		return false;
	}
}
