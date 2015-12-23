package de.htwg.backgammon.aview;

import java.util.Scanner;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.model.implementation.GameState;
import de.htwg.backgammon.model.implementation.InitPlayersState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class Tui implements Observer {
	// Alle Feldzahlen eingabe 1-24 intern 0 -23
	private GameState gs;
	private InitPlayersState ps;
	private Controller contr;
	private TuiSB bStringBuilder = new BackgammonStringBuilder();
	private Scanner sc = new Scanner(System.in).useDelimiter("\\s*\n\\s*");

	public Tui(Controller c) {
		System.out.println("Tui startet");
		contr = c;
		contr.add(this);
//		initNames();	
//		running();
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
		print(gs.getCurrent() + " ist am Zug:");
		String input;
		input = sc.next();
		contr.doAction(input);
	}

	public void printField() {
		print(bStringBuilder.getStringBuilder(gs).toString());
		print(bStringBuilder.getInformations(gs).toString());
	}

	@Override
	public void update(Event e) {
		System.out.println("update");
		if (e instanceof GameState) {
			GameState old = gs;
			gs = (GameState) e;
			if(old == null)
				running();
		}
		else if (e instanceof InitPlayersState){
			System.out.println("Ich muss name geben");
			ps = (InitPlayersState) e;
			getName();
		}
	}

	public void getName(){
		print("Spieler Name:");
		String s1 = sc.next();
		if(ps.getStatus() == 2)
			return;
		contr.setPlayer(s1);
	}
	
	public void print(String msg) {
		System.out.println(msg);
	}
}
