package de.htwg.backgammon;

import de.htwg.backgammon.aView.Tui;
import de.htwg.backgammon.controller.Controller;

public class Backgammon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO schichten aufbauen (controller,model usw..)
		
		Controller c = new Controller();
		Tui tui = new Tui(c);
		
		// while(continue){TUI welche dann auch continue auf false setzt,
		// solange endlosschleife}
	}

}
