package de.htwg.backgammon.model;

public class StandardMove extends AbstractMove {

	protected StandardMove(int a, int b, Spieler s, SpielFeld sf) {
		super(a, b, s, sf);
	}
	@Override
	public int move() {
		Stein attack;
		Stein beaten;
		attack = sf.getTriangle(a).remove();
		beaten = sf.getTriangle(b).add(attack);
		return putOnBar(beaten);
		
	}

}
