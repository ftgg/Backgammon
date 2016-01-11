package de.htwg.backgammon;

import java.util.Scanner;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.backgammon.aview.Tui;
import de.htwg.backgammon.aview.gui.Gui;
import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.model.IDice;
import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;
import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.Player;

public class Backgammon {

	private static Scanner sc = new Scanner(System.in);
	private static Gui gui;
	private static Tui tui;
	private static Controller c;

	private Backgammon() {
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new BackgammonModule());
		IPitch pitch = injector.getInstance(IPitch.class);
		IDice dice = injector.getInstance(IDice.class);
		
		IPlayer s1 = new Player("Frau Weiss", TokenColor.WHITE);
		IPlayer s2 = new Player("Herr Schwarz", TokenColor.BLACK);
		//c = new Controller(4);
		c = new Controller(pitch,dice,s1,s2);
		tui = new Tui(c);
		gui = new Gui(c);
		c.start();
		
		
		boolean continu = true;
		while (continu) {
			continu = tui.processInputLine(sc.nextLine());
		}


	}

}
