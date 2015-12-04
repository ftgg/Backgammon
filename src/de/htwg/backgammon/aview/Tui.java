package de.htwg.backgammon.aview;

import java.util.Scanner;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.controller.GameState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class Tui implements Observer {
	// Alle Feldzahlen eingabe 1-24 intern 0 -23
	private GameState gs;
	private Controller contr;
	private TuiSB bStringBuilder = new BackgammonStringBuilder();
	private Scanner sc = new Scanner(System.in);

	public Tui(Controller c) {
		contr = c;
		contr.add(this);
		initNames();
		running();
	}

	protected Tui() {
		// constructor for tests
	}

	private void initNames() {
		String s1;
		String s2;
		print("Hallo, bitte zwei Spielernamen angeben:");
		print("Name Spieler Weiss:");

		s1 = sc.next();
		print("Name Spieler Schwarz:");
		s2 = sc.next();

		print("Viel Spass " + s1 + " und " + s2);
		contr.setSpieler(s1, s2);
	}

	private void running() {
		while (!gs.getGameFinished()) {
			print(gs.getMessage());
			printField();
			eingabe();
		}
		print(gs.getMessage());
	}

	private void eingabe() {
		print(gs.getCurrent().getName() + " ist am Zug:");
		String input;
		input = sc.nextLine();
		contr.doAction(input);
	}

	public void printField() {
		print(bStringBuilder.getStringBuilder(gs).toString());
		print(bStringBuilder.getInformations(gs).toString());
	}

	@Override
	public void update(Event e) {
		if (e instanceof GameState) {
			gs = (GameState) e;
		}
	}

	public void print(String msg) {
		System.out.println(msg);
	}
}
