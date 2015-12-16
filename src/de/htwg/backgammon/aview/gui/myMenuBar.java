package de.htwg.backgammon.aview.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class myMenuBar extends JMenuBar {
	private JMenu data;
	private JMenuItem close;

	public myMenuBar() {
		data = new JMenu("Datei");
		data.setMnemonic(KeyEvent.VK_D);
		data.getAccessibleContext().setAccessibleDescription("Descriptions are for noobs!");
		close = new JMenuItem("close");
		close.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                    System.exit(0);
	                }
	            }
	        );
		data.add(close);
		
		this.add(data);
	}
}
