package de.htwg.backgammon.aview.gui;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui {

	@SuppressWarnings("deprecation")
	public Gui() {
		JFrame mainFrame = new JFrame("Backgammon");
		JPanel background = new JPanel();
		background.setLayout(new CardLayout());
		//background.add(new JLabel(new ImageIcon("images/background.jpg")));
		JLabel mainLabel = new MainLabel();
		background.add(mainLabel);
		
		mainFrame.setContentPane(background);
		//mainFrame.setContentPane(mainPanel); // add mainPanel to mainFrame
		mainFrame.pack();
		mainFrame.show();
	}

}
