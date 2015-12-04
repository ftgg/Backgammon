package de.htwg.backgammon.model;

public class ExitMove extends AbstractMove {

	private int[] stonesOnField;
	protected ExitMove(int a, int b, Spieler s, SpielFeld sf, int[] stonesOnField) {
		super(a, b, s, sf);
		this.stonesOnField = stonesOnField;
	}
	
	@Override
	public int move() {
		return removeStone(s);
	}
	
	private int removeStone(Spieler s) {
		int i = 0;
		if (s.getColor() == Stein.WHITE)
			i = 1;
		stonesOnField[i] = stonesOnField[i] - 1;
		return stonesOnField[i] == 0 ? 111 : 0;
	}
}
