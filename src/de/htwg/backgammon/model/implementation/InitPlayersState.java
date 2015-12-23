package de.htwg.backgammon.model.implementation;


import de.htwg.backgammon.util.Event;

public class InitPlayersState implements Event{
	private int status;
	
	public InitPlayersState(int status) {
		this.status = status;
	}
	
	public int getStatus(){
		return status;
	}
}
