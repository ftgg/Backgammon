package de.htwg.backgammon.aView;

import de.htwg.backgammon.controller.GameState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class tui implements Observer {

	GameState gs;
	
	

	
	private void print(){
		//TODO: Print
		System.out.print("TUI");
	}
	
	
	@Override
	public void update(Event e) {
		if(e instanceof GameState){
			gs = (GameState) e;
			print();
		}
			
	}
}
