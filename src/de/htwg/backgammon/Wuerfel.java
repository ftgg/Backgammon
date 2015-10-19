package de.htwg.backgammon;

public class Wuerfel {

	private int max;
	private int min;

	public Wuerfel() {
		this(1, 6);
	}

	public Wuerfel(int min, int max) {
		// max has to be greater than 0
		// min has to be less than max and greather than -1
		if (max <= 0 || min < 0 || min > max) {
			throw new IllegalArgumentException();
		}
		this.max = max;
		this.min = min;
	}

	public int[] wuerfeln() {
		int z1, z2;
		z1 = (int) (Math.random() * max) + min;
		z2 = (int) (Math.random() * max) + min;
		return new int[] { z1, z2 };
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

}
