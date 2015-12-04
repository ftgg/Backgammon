package de.htwg.backgammon.model;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

/**
 * SpielFeld contains a List of all aviable Fields in which the tokens are.
 */

public class SpielFeld {

	private static final int EXIT = -1;
	private static final int BAR = -2;
	private List<Dreieck> dreiecke;
	private Dreieck barblack; // Bar of Player one (White)
	private Dreieck barwhite; // Bar of Player two (Black)
	private int[] StonesOnField = { 0, 0 }; // Black 0 , White 1

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

		if (quadsize == 24) {
			initpos.put(0, 2);
			initpos.put(11, 5);
			initpos.put(16, 3);
			initpos.put(18, 5);
			fill(initpos);
		} else {
			initpos.put(0, 1);
			fill(initpos);
		}

	}

	/**
	 * fills the empty pitch
	 */
	private void fill(Map<Integer, Integer> initpos) {
		int color = Stein.getWhite();
		for (int invers = 0; invers < 2; invers++) {
			for (Map.Entry<Integer, Integer> current : initpos.entrySet()) {
				for (int i = 0; current.getValue() > i; i++) {
					dreiecke.get(Math.abs(current.getKey() - (dreiecke.size() - 1) * invers)).add(new Stein(color));
					StonesOnField[invers] = StonesOnField[invers] + 1;
				}
			}
			color = Stein.getBlack();
		}
	}

	public int[] getStonesOnField() {
		return StonesOnField;
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
		if (spieler.getColor() == Stein.getWhite()) {
			return barwhite.isEmpty();
		}
		return barblack.isEmpty();
	}

	public int getBarCount(int spieler) {
		if (spieler == Stein.getWhite()) {
			return barwhite.count();
		}
		return barblack.count();
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
		if (b == EXIT)
			return removeStone(s);
		Stein attack;
		if (a == BAR) {
			if (s.getColor() == Stein.getWhite())
				attack = barwhite.remove();
			else
				attack = barblack.remove();
		} else {
			attack = dreiecke.get(a).remove();
		}
		Stein beaten = dreiecke.get(b).add(attack);

		if (beaten == null) // target field was empty
			return 0;

		// Attack
		if (s.getColor() == Stein.getWhite())
			barblack.add(beaten);
		else
			barwhite.add(beaten);
		return 1;
	}

	private int removeStone(Spieler s) {
		int i = 0;
		if (s.getColor() == Stein.getWhite())
			i = 1;
		StonesOnField[i] = StonesOnField[i] - 1;
		return StonesOnField[i] == 0 ? 111 : 0;
	}

	public boolean isMovePossible(int a, int b, Spieler s) {
		// there is a token of the current player in field a
		if (a != BAR && dreiecke.get(a).getColor() != s.getColor())
			return false;
		// field b is attackable or own
		return (b == EXIT || dreiecke.get(b).unsecure() || dreiecke.get(b).getColor() == s.getColor());
	}

	public int countOfTriangles(int i) {
		return dreiecke.get(i).count();
	}

	public boolean indexInHome(int b, Spieler current) {
		int quarterRange = getSize() / 4;
		if (current.getColor() == Stein.getBlack()) {
			return valueInRange(b, 0, quarterRange - 1);
		} else {
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
		boolean athome = true;
		for (int i = 0; i < dreiecke.size(); i++) {
			if (dreiecke.get(i).getColor() != current.getColor()) {
				continue;
			} else {
				athome = athome && indexInHome(i, current);
			}
		}
		return athome;
	}

	public Dreieck getBarblack() {
		return barblack;
	}

	public Dreieck getBarwhite() {
		return barwhite;
	}

	public static int getExit() {
		return EXIT;
	}

	public static int getBar() {
		return BAR;
	}
}
