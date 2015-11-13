package de.htwg.backgammon.controller;

import de.htwg.backgammon.aView.Tui;
import de.htwg.backgammon.model.*;
import de.htwg.backgammon.util.Subject;

public class Controller extends Subject {
	private Spieler s1;
	private Spieler s2;
	private Spieler current;
	private SpielFeld sf;
	private Wuerfel w;
	private int[] zuege = { 0, 0, 0, 0 };


	// TODO Regeln beachten
	// immer abwechselnd fahren
	// wie gefahren werden darf, zugüberprüfung
	public Controller() {
		sf = new SpielFeld();// Standartgröße = original größe
		w = new Wuerfel();
		wuerfeln();	
	}

	public Controller(boolean a) {
		sf = new SpielFeld();// Standartgröße = original größe
		w = new Wuerfel();
		wuerfeln();
		setSpieler("Test1", "Test2");
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
		if (!verifyMove(act[0], act[1])) {
			notifyObs(new GameState(sf, zuege, "Nicht möglicher Zug!", current));
			return;
		}
		// korrekte Eingabe, also Spielen
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
		int res[] = { 0, 0 };

		if (s[0].equals("b")) {
			res[0] = sf.BAR;
		} else {
			res[0] = parseInt(s[0]);
		}

		if (s[1].equals("h")) {
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

	// TODO isBarEmpty() weil wegen Bar zuerst ausspielen
	// TODO zug 5+1 sind noch zwei züge, sollen aber als ein zug mit 6
	// realisiert werden
	// TODO von a nach b wie weit zum aus zuege löschen
	// prüfen ob zug überhaupt möglich(zahl gewürfelt)
	// wenn ja zug tätigen //wenn keine zuege mehr da = current = anderer
	// Spieler

	public void spielZug(int a, int b) {

		// zug theoretisch ausführen
		int result = sf.zug(a, b, current);
		String message;
		// TODO schauen ob a nach b mit zuege möglich
		verifyMove(a, b);
		// TODO zug aus liste löschen und prüfen ob machbar
		if (result == -1) {
			// ILLEGAL
			// TODO return an UI ausgabe von falscher zug
			message = "Zug nicht möglich!";
			return;
		} else if (result == 0) {
			bewege(a, b);
			// move und zug entfernen
			spielerwechsel();
			message = "";
		} else {
			schlage(a, b);
			// attack und Zug entfernen
			spielerwechsel();
			message = "";
		}
		// Subject Notify für update an UI
		notifyObs(new GameState(sf, zuege, message, current));
	}

	/**
	 * completes an attack
	 * 
	 * @param a
	 * @param b
	 */
	private void schlage(int a, int b) {
		loescheWurf(a, b);
	}

	/**
	 * completes an move
	 * 
	 * @param a
	 * @param b
	 */
	private void bewege(int a, int b) {
		// TODO evtl mit schlage() zusammenfassen

	}

	/**
	 * deleates the current move from zuege
	 */
	protected void loescheWurf(int a, int b) {

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
		// zug beginnt mit bar

		if (!isBarMoveValid(a, b))
			return false;

		// home
		if (sf.allHome(current) && b == sf.EXIT)
			return true;

		// ist zug von logik her möglich
		if (sf.isMovePossible(a, b, current))
			return true;
		return false;
	}

	private boolean isBarMoveValid(int a, int b) {
		if (sf.isBarEmpty(current))
			return true;
		if (a != sf.BAR) {
			return false;
		} else {
			if (!sf.isTargetPossible(b, current))
				return false;
			// zielfeld frei und etwas auf bar => b prüfen
			return sf.indexInHome(b, otherPlayer(current));
		}
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

	private void spielerwechsel() {
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
