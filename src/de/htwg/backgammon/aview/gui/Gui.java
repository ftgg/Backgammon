package de.htwg.backgammon.aview.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import de.htwg.backgammon.controller.Controller;


public class Gui extends JFrame{

	public MainPanel mainPanel; // Public just for tests
	private Controller c;
	
	public Gui(Controller c) {
		super("Backgammon");
		this.c = c;
		mainPanel = new MainPanel(c);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setJMenuBar(new myMenuBar());
		
		this.add(mainPanel);
		this.pack();
		//this.setResizable(false);
		this.setVisible(true);
		initplayers();
		
//		super("LayeredPane Demonstration");
//	    this.pack();
//	    setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//	    JLayeredPane lp = getLayeredPane();
//
//	    JButton top = new JButton();
//	    top.setBackground(Color.white);
//	    top.setBounds(20, 20, 50, 50);
//
//	    lp.add(top, new Integer(3));
//	    this.setVisible(true);
	}

	//TODO spielernamen eingeben
	private void initplayers(){
		c.setSpieler("White", "Black");
	}
}
