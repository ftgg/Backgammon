package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Stein;
import de.htwg.backgammon.util.Event;

import java.util.Arrays;

import de.htwg.backgammon.model.Dreieck;

public class GameState implements Event {

	private int[] whiteStones;
	private int[] blackStones;
	private int blackBar;
	private int whiteBar;
	private int[] zuege;
	private String message;

	GameState(SpielFeld sf, int[] z) {
		this(sf, z, "Update",0,0);
	}

	GameState(SpielFeld sf, int[] z, String m, int whiteBar, int blackBar) {
		zuege = z;
		message = m;
		blackStones = new int[sf.getSize()];
		whiteStones = new int[sf.getSize()];
		this.whiteBar = whiteBar;
		this.blackBar = blackBar;
		fillArrays(sf);
	}

	public int[] getWhiteStones() {
		return whiteStones;
	}

	public int[] getZuege() {
		return zuege;
	}

	public String getMessage() {
		return message;
	}

	public int[] getBlackStones() {
		return blackStones;
	}
	
	public int getBlackBar() {
		return blackBar;
	}

	public int getBlackWhiteBar() {
		return whiteBar;
	}

	private void fillArrays(SpielFeld sf) {
		for (int i = 0; i < sf.getSize(); i++) {
			Dreieck d = sf.getDreiecke(i);
			if (d.getColor() == Stein.WHITE) {
				whiteStones[i] = d.count();
				blackStones[i] = 0;
			} else if (d.getColor() == Stein.BLACK) {
				blackStones[i] = d.count();
				whiteStones[i] = 0;
			}else{
				whiteStones[i] = 0;
				blackStones[i] = 0;
			}
		}
	}

}
