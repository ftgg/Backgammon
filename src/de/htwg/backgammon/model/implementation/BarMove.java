package de.htwg.backgammon.model.implementation;

public class BarMove extends AbstractMove {

	protected BarMove(int a, int b, Spieler s, SpielFeld sf) {
		super(a, b, s, sf);
	}

	@Override
	public int move() {
		Stein attack;
		Stein beaten;

		attack = (Stein) currentbar.remove();
		beaten = (Stein) sf.getTriangle(b).add(attack);
		return putOnBar(beaten);
	}



}
