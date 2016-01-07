package de.htwg.backgammon.model.implementation;

import javax.swing.ImageIcon;

import de.htwg.backgammon.util.Event;

public class SelectState implements Event {

	private final int index;
	private final boolean top;
	private final int select;


	public SelectState(int index, boolean top, int b) {
		this.index = index;
		this.top = top;
		this.select = b;
	}

	public int getIndex() {
		return index;
	}

	public boolean isTop() {
		return top;
	}
	
	public int getSelect() {
		return select;
	}
}
