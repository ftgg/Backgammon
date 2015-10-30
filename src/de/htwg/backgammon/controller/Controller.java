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
	// wie gefahren werden darf, zug�berpr�fung
	public Controller() {
		Tui tui = new Tui(this);
		sf = new SpielFeld();// Standartgr��e = original gr��e
		w = new Wuerfel();	
		current = s1;
		notifyObs(new GameState(sf, zuege, "Spiel Beginnt", current));
	}
	
	public void setSpieler(String n1, String n2){
		s1 = new Spieler(n1,Stein.WHITE);
		s2 = new Spieler(n2,Stein.BLACK);
	}

	//Auf jedenfall 2 eingaben zb b 3 von bar nach feld nummer 3 oder 20 h f�r von 20 nach hause, oder 3 5
	public void doAction(String action){
		
		if(isHandEmpty()){
			wuerfeln();
			//TODO evtl ans ende der methode
			notifyObs(new GameState(sf, zuege, "Gewuerfelt", current));
		}
		else if(sf.isBarEmpty(current)){
			//TODO Spielzug von Bar
		}
		else{
			
		}
		//TODO gewonnen?
	}
	
	//public for test
	public int[] parseAction(String act){
		String[] s = act.split(" ");
		int res[] = {0,0};
		//TODO f�lle behandeln mit falscher Eingabe
		if(s[0].equals("b")){
			res[0] = -2;
		}else{
			res[0] = Integer.valueOf(s[0]);
		}
		
		if(s[1].equals("h")){
			res[1] = -1;
		}else{
			res[1] = Integer.valueOf(s[1]);
		}		
		
		
		return res;
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

	//TODO isBarEmpty() weil wegen Bar zuerst ausspielen
	
	
	
	
	public void spielZug(int a, int b) {
		// TODO zug 5+1 sind noch zwei z�ge, sollen aber als ein zug mit 6
		// realisiert werden
		
		// TODO von a nach b wie weit zum aus zuege l�schen
		// pr�fen ob zug �berhaupt m�glich(zahl gew�rfelt)
		// wenn ja zug t�tigen //wenn keine zuege mehr da = current = anderer
		// Spieler

		int result = sf.zug(a, b, current.getColor());
		String message;
		// TODO zug aus liste l�schen und pr�fen ob machbar
		if (result == -1) {
			// ILLEGAL
			// TODO return an UI ausgabe von falscher zug
			message = "Zug nicht m�glich!";
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
		// Subject Notify f�r update an UI
		notifyObs(new GameState(sf, zuege, message, current));
	}

	private boolean isHandEmpty(){
		for (int c : zuege) {
			if (c != 0)
				return false;
		}
		return true;
	}
	
	private void spielerwechsel() {
		// TODO schaut ob spieler nochmal ziehen darf oder ob anderer Spieler
		// dran ist
		if(isHandEmpty()){
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
	
	public int[] getZuege(){
		return zuege;
	}

}
