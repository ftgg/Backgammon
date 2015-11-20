package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.*;
import de.htwg.backgammon.util.Subject;

public class Controller extends Subject {
	private Spieler s1;
	private Spieler s2;
	public Spieler current;
	private SpielFeld sf;
	private Wuerfel w;
	private int[] zuege = { 0, 0, 0, 0 };

	public Controller() {
		sf = new SpielFeld();// Standartgröße = original größe
		w = new Wuerfel();
		wuerfeln();
	}

	/**
	 * Controller für Test umgebung
	 * 
	 * @param a
	 */
	public Controller(String name) {
		sf = new SpielFeld();// Standartgröße = original größe
		w = new Wuerfel();
		wuerfeln();
		setSpieler("Test1", name);
	}

	public void setSpieler(String n1, String n2) {
		s1 = new Spieler(n1, Stein.WHITE);
		s2 = new Spieler(n2, Stein.BLACK);
		current = s1;
		notifyObs(new GameState(sf, zuege, "Spiel Beginnt", current));
	}

	// Auf jedenfall 2 eingaben zb b 3 von bar nach feld nummer 3 oder 20 h für
	// von 20 nach hause, oder 3 5
	public void doAction(String s) {
		int[] act = parseAction(s);
		if (act[0] == -3 || act[1] == -3) {
			notifyObs(new GameState(sf, zuege, "Fehlerhafte Eingabe!", current));
			return;
		}
		spielZug(act[0], act[1]);
	}

	// public for test
	/**
	 * 
	 * @param act
	 *            String with two numbers or "h" | "b"
	 * @return two int's and -3 if illegalArgument
	 */
	public int[] parseAction(String act) {
		String[] s = act.split(" ");
		if (s.length != 2)
			return new int[] { -3, -3 };
		int[] res = { 0, 0 };

		if ("b".equals(s[0])) {
			res[0] = sf.BAR;
		} else {
			res[0] = parseInt(s[0]);
		}

		if ("h".equals(s[1])) {
			res[1] = sf.EXIT;
		} else {
			res[1] = parseInt(s[1]);
		}
		return res;
	}

	int parseInt(String s) {
		int a;
		try {
			a = Integer.parseInt(s);
		} catch (Exception e) {
			return -3;
		}
		return a - 1;
	}

	public void wuerfeln() {
		w.wuerfeln();
		if (w.isPasch()) {
			zuege[0] = w.getCurrent()[0];
			zuege[1] = w.getCurrent()[1];
			zuege[2] = w.getCurrent()[0];
			zuege[3] = w.getCurrent()[1];
		} else {
			zuege[0] = w.getCurrent()[0];
			zuege[1] = w.getCurrent()[1];
			zuege[2] = 0;
			zuege[3] = 0;
		}
	}

	public void spielZug(int a, int b) {
		if (!verifyMove(a, b)) {
			notifyObs(new GameState(sf, zuege, "Nicht möglicher Zug!", current));
			return;
		}

		int result = sf.zug(a, b, current);
		String message;
		if (result == 0) {
			removeThrow(a, b);
			// move und zug entfernen
			spielerwechsel();
			message = "";
		} else {
			removeThrow(a, b);
			// attack und Zug entfernen
			spielerwechsel();
			message = "";
		}
		// Subject Notify für Update an UI
		notifyObs(new GameState(sf, zuege, message, current));
	}

	/**
	 * deleates the current move from zuege
	 */
	protected void removeThrow(int a, int b) {
		int digit = Math.abs(a-b);
		for(int i = 0; i < 4; i++)
			if(zuege[i] == digit){
				zuege[i] = 0;
				break;
			}			
	}

	/**
	 * checks if move from a to b is possible with diced numbers
	 * 
	 * @param a
	 *            startposition
	 * @param b
	 *            targetposition
	 * @return true if move is possible with diced numbers
	 */
	protected boolean verifyMove(int a, int b) {

		int value = Math.abs(b - a);
		// zug passt zu keiner gewürfelten zahl
		if (value != zuege[0] && value != zuege[1] && value != zuege[2] && value != zuege[3])
			return false;
		return (isBarMoveValid(a, b)) && (sf.allHome(current) && b == sf.EXIT) 
				&& (sf.isMovePossible(a, b, current));
	}

	private boolean isBarMoveValid(int a, int b) {
		if (sf.isBarEmpty(current))
			return true;
		if (a != sf.BAR)
			return false;

		if (!sf.isMovePossible(sf.BAR, b, current))
			return false;
		// zielfeld frei und etwas auf bar => b prüfen
		return sf.indexInHome(b, otherPlayer(current));
	}

	private Spieler otherPlayer(Spieler c) {
		return c == s1 ? s2 : s1;
	}

	private boolean zuegeEmpty() {
		for (int c : zuege) {
			if (c != 0)
				return false;
		}
		return true;
	}

	public void spielerwechsel() {
		if (zuegeEmpty()) {
			if (current == s1)
				current = s2;
			else
				current = s1;
			wuerfeln();
		}
	}

	/**
	 * NUR EINE TESTMETHODE nicht zum gebrauch gedacht =)
	 * 
	 * @return Wuerfelergebnis
	 */
	public int[] getWuerfelC() {
		return w.getCurrent();
	}

	public int[] getZuege() {
		return zuege;
	}

}
