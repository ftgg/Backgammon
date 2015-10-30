package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Stein;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.model.Dreieck;

public class GameState implements Event {

	private int[] white;
	private int[] black;
	private int[] zuege;
	private String message;

	GameState(SpielFeld sf, int[] z) {
		this(sf, z, "Update");
	}

	GameState(SpielFeld sf, int[] z, String m) {
		// white =
		// black =
		// TODO white und black arrays auslesen
		zuege = z;
		message = m;
		black = new int[sf.getSize()];
		white = new int[sf.getSize()];
		fillArrays(sf);
	}

	private void fillArrays(SpielFeld sf) {
		for (int i = 0; i < sf.getSize(); i++) {
			Dreieck d = sf.getDreiecke(i);
			if (d.getColor() == Stein.WHITE) {
				black[i] = d.count();
				white[i] = 0;
			} else if (d.getColor() == Stein.BLACK) {
				white[i] = d.count();
				black[i] = 0;
			}
		}
	}

}
