package de.htwg.backgammon.aView;

import java.util.Scanner;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.controller.GameState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class Tui implements Observer {
	// Alle Feldzahlen eingabe 1-24 intern 0 -23
	private GameState gs;
	private Controller contr;
	private BackgammonStringBuilder bStringBuilder = new BackgammonStringBuilder();
	private Scanner sc = new Scanner(System.in);

	public Tui(Controller c) {
		contr = c;
		contr.add(this);
		initNames();
		running();
	}

	private void initNames() {
		String s1;
		String s2;
		System.out.println("Hallo, bitte zwei Spielernamen angeben:");
		System.out.println("Name Spieler Weiss:");

		s1 = sc.next();
		System.out.println("Name Spieler Schwarz:");
		s2 = sc.next();

		System.out.printf("Viel Spass %s und %s\n", s1, s2);
		contr.setSpieler(s1, s2);
	}

	private void running() {
		while (!gs.getGameFinished()) {
			System.out.println(gs.getMessage());
			printField();
			eingabe();
		}
		System.out.println(gs.getMessage());
	}

	private void eingabe() {
		System.out.println(gs.getCurrent().getName() + " ist am Zug:");
		String input;
		input = sc.nextLine();
		contr.doAction(input);
	}

	public void printField() {
		System.out.print(bStringBuilder.getStringBuilder(gs).toString());
		System.out.print(bStringBuilder.getInformations(gs).toString());
	}

	@Override
	public void update(Event e) {
		if (e instanceof GameState) {
			gs = (GameState) e;
		}

	}
}
