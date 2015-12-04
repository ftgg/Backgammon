package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.Pitch;

public class ParseUnitBar extends ParseUnit {

	ParseUnitBar(ActionParser a) {
		super(a);
	}

	@Override
	public void parse(String[] s) {
		if ("b".equals(s[0])) {
			actp.res[0] = Pitch.BAR;
		} else {
			actp.res[0] = parseInt(s[0]);
		}
		successor.parse(s);
	}

}
