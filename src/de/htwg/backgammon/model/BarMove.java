package de.htwg.backgammon.model;

import de.htwg.backgammon.model.implementation.SpielFeld;
import de.htwg.backgammon.model.implementation.Spieler;
import de.htwg.backgammon.model.implementation.Stein;

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
