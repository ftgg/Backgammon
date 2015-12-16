package de.htwg.backgammon.aview.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Field extends JLabel{

	private static final long serialVersionUID = 1L;
	private int id;
	

	public Field(ImageIcon icon, int id) {
		super(icon);
		this.id = id;
	}

	public int getID(){
		return id;
	}
	
}
