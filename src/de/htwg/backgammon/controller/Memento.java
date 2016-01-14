package de.htwg.backgammon.controller;

import java.io.Serializable;

import de.htwg.backgammon.model.implementation.GameState;

public class Memento implements Serializable {
	
	private final GameState gs;
	public Memento(GameState gs){
		this.gs = gs;
	}
	
	public GameState getGameState(){
		return gs;
	}
}
