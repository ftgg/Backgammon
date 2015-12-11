package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.IPitch;

public class ParseUnitHome extends ParseUnit {

	ParseUnitHome(ActionParser a) {
		super(a);
	}

	@Override
	public void parse(String[] s) {
		if ("h".equals(s[1])) {
			actp.res[1] = IPitch.EXIT;
		} else {
			actp.res[1] = parseInt(s[1]);
		}
	}
}
