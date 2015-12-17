package de.htwg.backgammon.aview.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import de.htwg.backgammon.controller.Controller;


public class Gui {

	public MainPanel mainPanel; // Public just for tests
	private Controller c;
	
	public Gui(Controller c) {
		this.c = c;
		JFrame mainFrame = new JFrame("Backgammon");
		mainPanel = new MainPanel(c);
		JLabel hallo = new JLabel();
		hallo.setText("hallo");
		
		mainFrame.setJMenuBar(new myMenuBar());
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		initplayers();
	}

	//TODO spielernamen eingeben
	private void initplayers(){
		c.setSpieler("White", "Black");
	}
}
