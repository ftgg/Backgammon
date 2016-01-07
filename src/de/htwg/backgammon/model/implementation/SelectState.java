package de.htwg.backgammon.model.implementation;

import javax.swing.ImageIcon;

import de.htwg.backgammon.util.Event;

public class SelectState implements Event {

	private int index;
	private boolean top;
	private boolean select;


	public SelectState(int index, boolean top, boolean b) {
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
	
	public boolean getSelect() {
		return select;
	}
}
