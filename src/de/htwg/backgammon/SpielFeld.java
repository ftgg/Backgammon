package de.htwg.backgammon;

public class SpielFeld {

	private Dreieck[] dreiecke;
	private int size;

	public SpielFeld() {
		this(6);
	}

	public SpielFeld(int size) {
		this.size = size * 4;
	}

	public int getSize() {
		return size;
	}

	public Dreieck getDreiecke(int i) {
		return dreiecke[i];
	}
}
