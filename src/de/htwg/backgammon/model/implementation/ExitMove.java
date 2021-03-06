package de.htwg.backgammon.model.implementation;

import de.htwg.backgammon.model.TokenColor;

public class ExitMove extends AbstractMove {

	private int[] stonesOnField;
	protected ExitMove(int a, int b, Player s, Pitch sf, int[] stonesOnField) {
		super(a, b, s, sf);
		this.stonesOnField = stonesOnField;
	}
	
	@Override
	public int move() {
		return removeStone(s);
	}
	
	private int removeStone(Player s) {
		int i = 0;
		if (s.getColor() == TokenColor.WHITE)
			i = 1;
		stonesOnField[i] = stonesOnField[i] - 1;
		sf.getTriangle(a).remove();
		return stonesOnField[i] == 0 ? 111 : 0;
	}
}
