package de.htwg.backgammon;

import java.util.Scanner;

import de.htwg.backgammon.aview.Tui;
import de.htwg.backgammon.aview.gui.Gui;
import de.htwg.backgammon.controller.Controller;

public class Backgammon {

	private static Scanner sc = new Scanner(System.in);
	private static Gui gui;
	private static Tui tui;
	private static Controller c;
	
	
	private Backgammon() {
	}

	public static void main(String[] args) {
		c = new Controller();
		tui =new Tui(c);
		gui =new Gui(c);
		c.create();
		
		boolean continu = true;
		while(continu){
			continu = tui.processInputLine(sc.nextLine());
		}
		
	}

}
