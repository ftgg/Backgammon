package de.htwg.backgammon.model;

public class BarMove extends AbstractMove {

	protected BarMove(int a, int b, Spieler s, SpielFeld sf) {
		super(a, b, s, sf);
	}

	@Override
	public int move() {
		Stein attack;
		Stein beaten;

		attack = currentbar.remove();
		beaten = sf.getTriangle(b).add(attack);
		return putOnBar(beaten);
	}



}
