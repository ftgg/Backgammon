package de.htwg.backgammon.model.implementation;

import de.htwg.backgammon.model.IDice;

public class Dice implements IDice {

	private int max;
	private int min;
	private int[] current; // zuletzt gewürfeltes ergebnis
	private boolean isPasch;

	public Dice() {
		this(1, 6);
	}

	public Dice(int min, int max) {
		isPasch = false;
		// max has to be greater than 0
		// min has to be less than max and greather than -1
		if (max <= 0 || min < 0 || min > max) {
			throw new IllegalArgumentException();
		}
		this.max = max;
		this.min = min;
		current = new int[2];
	}

	public int[] wuerfeln() {
		int z1, z2;
		z1 = (int) (Math.random() * max) + min;
		z2 = (int) (Math.random() * max) + min;
		current[0] = z1;
		current[1] = z2;
		if (z1 == z2)
			isPasch = true;
		else
			isPasch = false;
		return current;
	}

	@Override
	public int getMax() {
		return max;
	}

	@Override
	public int getMin() {
		return min;
	}

	@Override
	public boolean isDoublets() {
		return isPasch;
	}

	@Override
	public int[] getCurrentCubeNumbers() {
		return current;
	}

	@Override
	public int[] rollTheDice() {
		return wuerfeln();
	}

}
