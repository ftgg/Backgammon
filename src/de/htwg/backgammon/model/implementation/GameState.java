package de.htwg.backgammon.model.implementation;

import de.htwg.backgammon.model.ITriangle;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;
import de.htwg.backgammon.model.TokenColor;

public class GameState implements Event {
	/*Restorefunctions in Controller, to restore GameState*/
	private int[] whiteStones;
	private int[] blackStones;
	private int blackBar;
	private int whiteBar;
	private int[] zuege;
	private String message;
	private IPlayer current;
	private boolean gamefinished = false;

	public GameState(IPitch sf, int[] z, IPlayer s) {
		this(sf, z, "Update", s, false);
	}

	public GameState(IPitch sf, int[] z, String m, IPlayer s, boolean w) {
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
	
	public IPlayer getCurrent() {
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

	private void fillArrays(IPitch sf) {
		for (int i = 0; i < sf.getSize(); i++) {
			ITriangle d = sf.getTriangle(i);
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
