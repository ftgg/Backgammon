package de.htwg.backgammon.aview.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class myMenuBar extends JMenuBar {
	private JMenu data;
	private JMenu game;
	private JMenuItem close;
	private JMenuItem undo;
	private JMenuItem replay;
	private JMenuItem colorTheme;
	private Gui gui;

	public myMenuBar(Gui gui) {
		this.gui = gui;
		data = new JMenu("Datei");
		data.setMnemonic(KeyEvent.VK_D);
		data.getAccessibleContext().setAccessibleDescription("Descriptions are for noobs!");
		close = new JMenuItem("Beenden");
		close.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                    System.exit(0);
	                }
	            }
	        );
		colorTheme = new JMenuItem("Schema");
		colorTheme.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	//TODO
	                }
	            }
	        );
		data.add(close);
		data.add(colorTheme);
		
		game = new JMenu("Spiel");
		game.setMnemonic(KeyEvent.VK_S);
		undo = new JMenuItem("Rückgängig");
		undo.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	gui.c.undo();
	                }
	            }
	        );
		replay = new JMenuItem("Wiederholung");
		replay.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	//TODO
	                }
	            }
	        );
		game.add(undo);
		game.add(replay);
		
		this.add(data);
		this.add(game);
	}
}
