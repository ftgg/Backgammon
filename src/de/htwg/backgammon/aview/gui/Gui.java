package de.htwg.backgammon.aview.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.model.implementation.InitPlayersState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class Gui extends JFrame implements Observer {

	public MainPanel mainPanel; // Public just for tests
	private InitPlayersState ps;
	protected Controller c;

	public Gui(Controller c) {
		super("Backgammon");
		this.c = c;
		c.add(this);
		mainPanel = new MainPanel(c);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setJMenuBar(new myMenuBar(this));

		this.add(mainPanel);
		this.pack();
		// this.setResizable(false);
		this.setVisible(true);
		// initplayers();
	}

	// TODO spielernamen eingeben
	private void initplayers() {
		c.setSpieler("White", "Black");
	}

	@Override
	public void update(Event e) {
		if (e instanceof InitPlayersState) {
			System.out.println("GUI UPDATE");
			ps = (InitPlayersState) e;
			if (ps.getStatus() != 2)
				getPName();
		}
	}

	public void getPName() {
		String s1 = JOptionPane.showInputDialog(null, "Spieler Name:", "Eingabe", JOptionPane.PLAIN_MESSAGE);
		if (ps.getStatus() == 2)
			// return;
			c.setPlayer(s1);
	}
}
