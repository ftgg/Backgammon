package de.htwg.backgammon.controller;

import de.htwg.backgammon.aview.Tui;

public abstract class ParseUnit {
	ParseUnit successor;
	ActionParser actp;
	
	ParseUnit(ActionParser a){
		actp = a;
	}
	
	public abstract void parse(String[] s);
	
	int parseInt(String s) {
		int a;
		try {
			a = Integer.parseInt(s);
		} catch (Exception e) {
			return -3;
		}
		if (a < 1)
			return -3;
		return a - 1;
	}
	
}
