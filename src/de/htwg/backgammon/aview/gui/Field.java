package de.htwg.backgammon.aview.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Field extends JPanel{

	private static final long serialVersionUID = 1L;
	private int id;
	private JLayeredPane layeredPane;
	private JLabel background;
	public JLabel foreground;
	public MainPanel mp;
	

	public Field(ImageIcon icon, int id, MainPanel mp) {
		super();
		this.id = id;
		this.mp = mp;
		
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(100, 300));
		layeredPane.setLayout(null);
		
		background = new JLabel(icon);
		foreground = new JLabel(mp.ct.getnoToken());
		layeredPane.add(background, new Integer(1),0);
		layeredPane.moveToBack(background);
		layeredPane.add(foreground, new Integer(2),0);
		layeredPane.moveToFront(foreground);
		
		background.setBounds(0, 0, 100, 300);
		foreground.setBounds(0, 0, 100, 300);
		
//		this.add(background);
//		this.add(foreground);
		this.add(layeredPane);
	}

	public int getID(){
		return id;
	}
	
}
