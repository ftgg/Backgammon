package de.htwg.backgammon.aview.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenuBar extends JMenuBar {
	private JMenu data;
	private JMenu game;
	private JButton next;
	private JMenuItem undo;
	private JMenuItem replay;
	private JMenuItem load;
	private JMenuItem save;
	private JMenuItem colorTheme;
	private final JFileChooser fc = new JFileChooser();

	public MyMenuBar(Gui gui) {
		
		next = new JButton("Next");
		next.setPreferredSize(new Dimension(15,this.getHeight()));
		next.setMnemonic(KeyEvent.VK_N);
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.c.next();
			}
		});
	
		data = new JMenu("Datei");
		data.setMnemonic(KeyEvent.VK_D);
		data.getAccessibleContext().setAccessibleDescription("Descriptions are for noobs!");
		colorTheme = new JMenuItem("Schema");
		colorTheme.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// not implemented yet because its to late for more features
			}
		});
		data.add(colorTheme);

		game = new JMenu("Spiel");
		game.setMnemonic(KeyEvent.VK_S);
		undo = new JMenuItem("Rückgängig");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.c.undo();
			}
		});
		replay = new JMenuItem("Wiederholung");
		replay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.c.replayGame();
			}
		});
		load = new JMenuItem("Laden");
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					gui.c.loadGame(file);
				}
			}
		});
		save = new JMenuItem("Speichern");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showSaveDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					gui.c.saveGame(file);
				}
			}
		});
		game.add(undo);
		game.add(replay);
		game.add(load);
		game.add(save);
		
		this.add(data);
		this.add(game);
		this.add(next);
	}
}