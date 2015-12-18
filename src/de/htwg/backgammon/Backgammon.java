package de.htwg.backgammon;

import de.htwg.backgammon.aview.Tui;
import de.htwg.backgammon.aview.gui.Gui;
import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.model.TokenColor;

public class Backgammon {

	private Backgammon(){}
	
	public static void main(String[] args) {
		Controller c = new Controller();
//		new Tui(c);
		Gui gui = new Gui(c);
	}

}
