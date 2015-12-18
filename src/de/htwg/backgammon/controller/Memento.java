package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.implementation.GameState;

public class Memento {
	private final GameState gs;
	public Memento(GameState gs){
		this.gs = gs;
	}
	
	public GameState getGameState(){
		return gs;
	}
}
