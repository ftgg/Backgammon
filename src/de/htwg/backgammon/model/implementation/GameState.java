package de.htwg.backgammon.model.implementation;


import de.htwg.backgammon.util.Event;

import java.io.Serializable;
import java.util.Arrays;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;
import de.htwg.backgammon.model.TokenColor;

public class GameState implements Event,Serializable {

	private int[] whiteStones;
	private int[] blackStones;
	private int whiteStonesOnPitch = 0;
	private int blackStonesOnPitch = 0;
	private int blackBar;
	private int whiteBar;
	private int[] zuege;
	private String message;
	private TokenColor current;
	private TokenColor p1c;
	private TokenColor p2c;
	private String p1n;
	private String p2n;
	private boolean gamefinished = false;

	
	public GameState(IPitch sf, int[] z, IPlayer s, IPlayer s1, IPlayer s2) {
		this(sf, z, "Update", s, false, s1, s2);
	}

	public GameState(IPitch sf, int[] z, String m, IPlayer s, boolean w, IPlayer s1, IPlayer s2) {
		zuege = z;
		message = m;
		blackStones = new int[sf.getSize()];
		whiteStones = new int[sf.getSize()];
		this.whiteBar = sf.getBarCount(TokenColor.WHITE);
		this.blackBar = sf.getBarCount(TokenColor.BLACK);
		current = s.getColor();
		p1c = s1.getColor();
		p1n = s1.getName();
		p2c = s2.getColor();
		p2n = s2.getName();
		
		gamefinished = w;
		fillArrays(sf);
	}

	public boolean getGameFinished(){
		return gamefinished;
	}
	
	public IPlayer[] getPlayer(){
		return new IPlayer[]{new Player(p1n,p1c),new Player(p2n,p2c)};
	}
	
	public IPlayer getCurrent() {
		if(current == p1c)
			return new Player(p1n,p1c);
		return new Player(p2n,p2c);
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
	
	public int getWhiteStonesOnPitch(){
		return whiteStonesOnPitch + whiteBar;
	}
	
	public int getBlackStonesOnPitch(){
		return blackStonesOnPitch + blackBar;
	}
	

	private void fillArrays(IPitch sf) {
		Triangle d;
		for (int i = 0; i < sf.getSize(); i++) {
			if(sf instanceof Pitch){
				d = ((Pitch)sf).getTriangle(i);
			}else{
				throw new ClassCastException();
			}
			
			if (d.getColor() == TokenColor.WHITE) {
				whiteStones[i] = d.count();
				blackStones[i] = 0;
			} else if (d.getColor() == TokenColor.BLACK) {
				blackStones[i] = d.count();
				blackStonesOnPitch += blackStones[i];
				whiteStones[i] = 0;
			} else {
				whiteStones[i] = 0;
				whiteStonesOnPitch += whiteStones[i];
				blackStones[i] = 0;
			}
		}
	}
	
	public static GameState getDefaultGameState(){
		return new GameState(); 
	}
	
	private GameState(){
		int[] newwhiteStones = { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0, 0 };
		int[] newblackStones = { 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 };
		int[] newzuege = {0,0,0,0};
		this.zuege = newzuege;
		this.blackStones = newblackStones;
		this.whiteStones = newwhiteStones;
		this.whiteBar = 0;
		this.blackBar = 0;
		current = null;
		gamefinished = false;
	}
	
	public static GameState getTestGameState(int i){
		if(i == 2){
			return new GameState(new int[] { 1,0,0,0,0,0,0,0 },new int[]{ 0,0,0,0,0,0,0,1},1,0);
		}else if (i == 1){
			return new GameState(new int[] { 1,0,0,0 },new int[]{ 0,0,0,1 },1,0);
		}else if (i == 3){ // mit stein auf Bar
			return new GameState(new int[] { 1,0,0,0 },new int[]{ 0,0,0,1 },1,1);
		}else if (i == 4){
			return new GameState(new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
					new int[]{1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},2,0);
		}
		return getDefaultGameState();
	}
	
	//For testStates
	private GameState(int[] whiteStones, int[] blackStones, int onPitch, int whiteBar){
		int[] newzuege = {0,0,0,0};
		this.zuege = newzuege;
		this.blackStones = blackStones;
		this.whiteStones = whiteStones;
		this.whiteBar = whiteBar;
		this.blackBar = 0;
		this.whiteStonesOnPitch = onPitch;
		this.blackStonesOnPitch = onPitch;
		current = TokenColor.WHITE;
		gamefinished = false;
	}
	
	

}
