package de.htwg.backgammon;

import de.htwg.backgammon.aview.Tui;
import de.htwg.backgammon.controller.Controller;

public class Backgammon {

	private Backgammon(){}
	
	public static void main(String[] args) {
		Controller c = new Controller(2);
		new Tui(c);
	}

}
