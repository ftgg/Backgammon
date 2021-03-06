package de.htwg.backgammon.model.implementation;

public class StandardMove extends AbstractMove {

	protected StandardMove(int a, int b, Player s, Pitch sf) {
		super(a, b, s, sf);
	}
	@Override
	public int move() {
		Token attack;
		Token beaten;
		attack = sf.getTriangle(a).remove();
		beaten = sf.getTriangle(b).add(attack);
		return putOnBar(beaten);
		
	}

}
