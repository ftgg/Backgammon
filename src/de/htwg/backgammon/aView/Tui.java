package de.htwg.backgammon.aView;

import java.util.Scanner;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.controller.GameState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class Tui implements Observer {

	private GameState gs;
	private Controller contr;

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
		Scanner s = new Scanner(System.in);
		s1 = s.next();
		System.out.println("Name Spieler Schwarz:");
		s2 = s.next();
		s.close();
		System.out.printf("Viel Spass %s und %s\n", s1, s2);
		contr.setSpieler(s1, s2);
	}

	private void running() {
		while (!gs.getGameFinished()) {
			System.out.println(gs.getMessage());
			print();
			eingabe();
		}
	}

	private void eingabe(){
		System.out.println(gs.getCurrent().getName() + " ist am Zug:");
		Scanner s = new Scanner(System.in);
		String t = s.nextLine();
		s.close();
		contr.doAction(t);
	}
	
	public void print() {

	}

	@Override
	public void update(Event e) {
		if (e instanceof GameState) {
			gs = (GameState) e;
		}

	}
}
