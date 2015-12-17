package de.htwg.backgammon.aview.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Field extends JLabel{

	private static final long serialVersionUID = 1L;
	private int id;
	public MainPanel mp;
	

	public Field(ImageIcon icon, int id, MainPanel mp) {
		super(icon);
		this.id = id;
		this.mp = mp;
	}

	public int getID(){
		return id;
	}
	
}
