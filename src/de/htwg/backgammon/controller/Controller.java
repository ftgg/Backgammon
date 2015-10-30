package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.*;

public class Controller {
	private Spieler s1, s2, current;
	private SpielFeld sf;
	private Wuerfel w;
	private int[] zuege = { 0, 0, 0, 0 };

	// TODO Baut Modelschicht auf
	// 2 Spieler, spielfeld, ein w�rfel
	// TODO Regeln beachten
	// immer abwechselnd fahren
	// wie gefahren werden darf, zug�berpr�fung
	public Controller() {
		s1 = new Spieler("NAME", Stein.White);
		s2 = new Spieler("NAME2", Stein.Black);
		current = s1;
		sf = new SpielFeld();// Standartgr��e = original gr��e
		w = new Wuerfel();
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
			zuege[1] = w.getCurrent()[0];
			zuege[2] = 0;
			zuege[3] = 0;
		}
		return zuege;
	}

	public void spielZug(int a, int b) {
		// TODO von a nach b wie weit zum aus zuege l�schen
		// pr�fen ob playerbar frei oder nicht !ZUERST
		// pr�fen ob zug �berhaupt m�glich
		// wenn ja zug t�tigen //wenn keine zuege mehr da = current = anderer
		// Spieler
		int zugreturn = sf.zug(a, b, current.getColor());
		if (zugreturn == -1) {
			// ILLEGAL
			// TODO return an UI ausgabe von falscher zug
			return;
		} else if (zugreturn == 0) {
			// move

			spielerwechsel();
		} else {
			// attack

			spielerwechsel();
		}

	}

	private void spielerwechsel() {
		// TODO schaut ob spieler nochmal ziehen darf oder ob anderer Spieler
		// dran ist
	}

}
