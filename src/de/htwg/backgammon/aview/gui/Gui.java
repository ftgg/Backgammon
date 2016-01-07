package de.htwg.backgammon.aview.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.model.implementation.InitPlayersState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class Gui extends JFrame implements Observer  {

	public MainPanel mainPanel; // Public just for tests
	private JLayeredPane pitch;
	private InitPlayersState ps;
	protected Controller c;

	public Gui(Controller c) {
		super("Backgammon");
		this.c = c;
		c.add(this);
		mainPanel = new MainPanel(c);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pitch = new JLayeredPane();

		pitch.setPreferredSize(new Dimension(1000,500));
		pitch.setLayout(null);
		mainPanel.setBounds(0, 0, 1000, 500);
		pitch.add(mainPanel, new Integer(1), 0);
		this.setPreferredSize(new Dimension(1000,500));
		
		System.out.println(this.getSize());
		
		
		this.setJMenuBar(new myMenuBar(this));
		this.add(pitch);
		this.pack();
		// this.setResizable(false);
		this.setVisible(true);
		// initplayers();
		
		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent e) {
				//pitch.setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
				mainPanel.setSize(e.getComponent().getWidth(), e.getComponent().getHeight()-60);
				mainPanel.resize();
				pitch.repaint();
				mainPanel.repaint();
			}

			@Override
			public void componentShown(ComponentEvent e) {
				
			}
		});
		
		
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
