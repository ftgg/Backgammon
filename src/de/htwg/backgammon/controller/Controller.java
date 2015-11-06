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

	private static final int HOME = -1;
	private static final int BAR = -2;

	// TODO Regeln beachten
	// immer abwechselnd fahren
	// wie gefahren werden darf, zugüberprüfung
	public Controller() {
		Tui tui = new Tui(this);
		sf = new SpielFeld();// Standartgröße = original größe
		w = new Wuerfel();
		current = s1;
		notifyObs(new GameState(sf, zuege, "Spiel Beginnt", current));
	}

	public void setSpieler(String n1, String n2) {
		s1 = new Spieler(n1, Stein.WHITE);
		s2 = new Spieler(n2, Stein.BLACK);
	}

	// Auf jedenfall 2 eingaben zb b 3 von bar nach feld nummer 3 oder 20 h für
	// von 20 nach hause, oder 3 5
	public void doAction(String action) {

		if (isHandEmpty()) {
			wuerfeln();
			// TODO evtl ans ende der methode
			notifyObs(new GameState(sf, zuege, "Gewuerfelt", current));
		} else if (sf.isBarEmpty(current)) {
			// TODO Spielzug von Bar
		} else {

		}
		// TODO gewonnen?
	}

	// public for test
	/**
	 * 
	 * @param act String with two numbers or "h" | "b"
	 * @return two ints and -3 if illegalArgument
	 */
	public int[] parseAction(String act) {
		String[] s = act.split(" ");
		int res[] = { 0, 0 };
		// TODO fälle behandeln mit falscher Eingabe
		if (s[0].equals("b")) {
			res[0] = BAR;
		} else {
			res[0] = parseInt(s[0]);
		}

		if (s[1].equals("h")) {
			res[1] = HOME;
		} else {
			res[1] = parseInt(s[1]);
		}
		return res;
	}
	
	private int parseInt(String s){
		int a;
		try{
			a = Integer.parseInt(s);
		}catch(Exception e){
			return -3;
		} 
		return a;
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

	public void spielZug(int a, int b) {
		// TODO zug 5+1 sind noch zwei züge, sollen aber als ein zug mit 6
		// realisiert werden

		// TODO von a nach b wie weit zum aus zuege löschen
		// prüfen ob zug überhaupt möglich(zahl gewürfelt)
		// wenn ja zug tätigen //wenn keine zuege mehr da = current = anderer
		// Spieler

		int result = sf.zug(a, b, current.getColor());
		String message;
		// TODO zug aus liste löschen und prüfen ob machbar
		if (result == -1) {
			// ILLEGAL
			// TODO return an UI ausgabe von falscher zug
			message = "Zug nicht möglich!";
			return;
		} else if (result == 0) {
			// move und zug entfernen

			spielerwechsel();
			message = "";
		} else {
			// attack und Zug entfernen

			spielerwechsel();
			message = "";
		}
		// Subject Notify für update an UI
		notifyObs(new GameState(sf, zuege, message, current));
	}

	private boolean isHandEmpty() {
		for (int c : zuege) {
			if (c != 0)
				return false;
		}
		return true;
	}

	private void spielerwechsel() {
		// TODO schaut ob spieler nochmal ziehen darf oder ob anderer Spieler
		// dran ist
		if (isHandEmpty()) {
			if (current == s1)
				current = s2;
			else
				current = s1;
		}
	}

	/**
	 * NUR EINE TESTMETHODE nicht zum gebrauch gedacht =)
	 * 
	 * @return ja
	 */
	public int[] getWuerfelC() {
		return w.getCurrent();
	}

	public int[] getZuege() {
		return zuege;
	}

}
