package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.Triangle;
import de.htwg.backgammon.model.implementation.SpielFeld;

import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.Player;
import de.htwg.backgammon.model.TokenColor;

public class GameState implements Event {

	private int[] whiteStones;
	private int[] blackStones;
	private int blackBar;
	private int whiteBar;
	private int[] zuege;
	private String message;
	private Player current;
	private boolean gamefinished = false;

	GameState(SpielFeld sf, int[] z, Player s) {
		this(sf, z, "Update", s, false);
	}

	public GameState(Pitch sf, int[] z, String m, Player s, boolean w) {
		zuege = z;
		message = m;
		blackStones = new int[sf.getSize()];
		whiteStones = new int[sf.getSize()];
		this.whiteBar = sf.getBarCount(TokenColor.WHITE);
		this.blackBar = sf.getBarCount(TokenColor.BLACK);
		current = s;
		gamefinished = w;
		fillArrays(sf);
	}

	public boolean getGameFinished(){
		return gamefinished;
	}
	
	public Player getCurrent() {
		return current;
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

	public int getWhiteBar() {
		return whiteBar;
	}

	private void fillArrays(Pitch sf) {
		for (int i = 0; i < sf.getSize(); i++) {
			Triangle d = sf.getTriangle(i);
			if (d.getColor() == TokenColor.WHITE) {
				whiteStones[i] = d.count();
				blackStones[i] = 0;
			} else if (d.getColor() == TokenColor.BLACK) {
				blackStones[i] = d.count();
				whiteStones[i] = 0;
			} else {
				whiteStones[i] = 0;
				blackStones[i] = 0;
			}
		}
	}

}
