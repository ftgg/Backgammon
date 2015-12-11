package de.htwg.backgammon.model.implementation;

import de.htwg.backgammon.model.IToken;

public class StandardMove extends AbstractMove {

	protected StandardMove(int a, int b, Player s, Pitch sf) {
		super(a, b, s, sf);
	}
	@Override
	public int move() {
		IToken attack;
		IToken beaten;
		attack = sf.getTriangle(a).remove();
		beaten = sf.getTriangle(b).add(attack);
		return putOnBar(beaten);
		
	}

}
