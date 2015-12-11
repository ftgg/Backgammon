package de.htwg.backgammon.controller;

public abstract class ParseUnit {
	ParseUnit successor;
	ActionParser actp;
	
	ParseUnit(ActionParser a){
		actp = a;
	}
	
	public abstract void parse(String s[]);
	
	int parseInt(String s) {
		int a;
		try {
			a = Integer.parseInt(s);
		} catch (Exception e) {
			System.err.println(e);
			return -3;
		}
		if (a < 1)
			return -3;
		return a - 1;
	}
	
	
	
}
