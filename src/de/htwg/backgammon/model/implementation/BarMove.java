package de.htwg.backgammon.model.implementation;

public class BarMove extends AbstractMove {

	protected BarMove(int a, int b, Player s, Pitch sf) {
		super(a, b, s, sf);
	}

	@Override
	public int move() {
		Token attack;
		Token beaten;

		attack = (Token) currentbar.remove();
		beaten = (Token) sf.getTriangle(b).add(attack);
		return putOnBar(beaten);
	}



}
