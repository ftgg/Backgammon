package de.htwg.backgammon.model.implementation;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.Player;
import de.htwg.backgammon.model.Triangle;
import de.htwg.backgammon.model.TestPitch;

import java.util.List;

/**
 * SpielFeld contains a List of all aviable Fields in which the tokens are.
 */

public class SpielFeld implements Pitch, TestPitch {

	private List<Triangle> dreiecke;
	private Triangle barblack; // Bar of Player one (White)
	private Triangle barwhite; // Bar of Player two (Black)
	private int[] stonesOnField = { 0, 0 }; // Black 0 , White 1

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
		dreiecke = new LinkedList<Triangle>();
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

	private void fill(Map<Integer, Integer> initpos) {
		int color = Stein.WHITE;
		for (int invers = 0; invers < 2; invers++) {
			for (Map.Entry<Integer, Integer> current : initpos.entrySet()) {
				for (int i = 0; current.getValue() > i; i++) {
					dreiecke.get(Math.abs(current.getKey() - (dreiecke.size() - 1) * invers)).add(new Stein(color));
					stonesOnField[invers] = stonesOnField[invers] + 1;
				}
			}
			color = Stein.BLACK;
		}
	}

	@Override
	public int[] getTokensOnTriangle() {
		return stonesOnField;
	}

	@Override
	public int getSize() {
		return dreiecke.size();
	}

	private Triangle getDreiecke(int i) {
		return dreiecke.get(i);
	}

	@Override
	public boolean isEmpty(int i) {
		return dreiecke.get(i).isEmpty();
	}

	@Override
	public boolean isBarEmpty(Player spieler) {
		if (spieler.getColor() == Stein.WHITE)
			return barwhite.isEmpty();
		return barblack.isEmpty();
	}

	@Override
	public int getBarCount(int spieler) {
		if (spieler == Stein.WHITE) {
			return barwhite.count();
		}
		return barblack.count();
	}

	@Override
	public int move(int a, int b, Player s) {
		AbstractMove m = AbstractMove.createMoveObject(a, b, (Spieler) s, this, stonesOnField);
		return m.move();
	}

	@Override
	public int countOfTriangle(int i) {
		return dreiecke.get(i).count();
	}

	@Override
	public boolean indexInHome(int b, Player current) {
		int quarterRange = getSize() / 4;
		if (current.getColor() == Stein.BLACK) {
			return valueInRange(b, 0, quarterRange - 1);
		} else {
			return valueInRange(b, quarterRange * 3, quarterRange * 4 - 1);
		}
	}

	private boolean valueInRange(int value, int min, int max) {
		return !(value > max || value < min);
	}

	@Override
	public boolean allHome(Player current) {
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

	@Override
	public Triangle getBarblack() {
		return barblack;
	}

	@Override
	public Triangle getBarwhite() {
		return barwhite;
	}

	@Override
	public Triangle getTriangle(int i) {
		return getDreiecke(i);
	}

}
