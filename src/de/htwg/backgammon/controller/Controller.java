package de.htwg.backgammon.controller;

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
		//TODO UI erstellen und setSpieler aufrufen. danach nie wieder setSpieler! 
		setSpieler("Hans","Hatkeinelust");
		current = s1;
		sf = new SpielFeld();// Standartgröße = original größe
		w = new Wuerfel();
	}
	
	public void setSpieler(String n1, String n2){
		s1 = new Spieler(n1,Stein.WHITE);
		s2 = new Spieler(n2,Stein.BLACK);
	}

	public int[] wuerfeln() {
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
		return zuege;
	}

	//TODO isBarEmpty() weil wegen Bar zuerst ausspielen
	
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

	private void spielerwechsel() {
		// TODO schaut ob spieler nochmal ziehen darf oder ob anderer Spieler
		// dran ist
		for (int c : zuege) {
			if (c != 0)
				return;
		}
		// spielerwechsel
		if (current == s1)
			current = s2;
		else
			current = s1;
	}

	/**
	 * NUR EINE TESTMETHODE nicht zum gebrauch gedacht =)
	 * 
	 * @return ja
	 */
	public int[] getWuerfelC() {
		return w.getCurrent();
	}

}
