package de.htwg.backgammon.aview.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainLabel extends JLabel{

	private JPanel top;
	private JPanel mid;
	private JPanel bot;
	
	public MainLabel(){
		this.setLayout(new BorderLayout());
		ImageIcon botone = new ImageIcon("images/dark.png");
		ImageIcon bottwo = new ImageIcon("images/light.png");
		//TODO JLabels werden noch nicht angezeigt
		top = new IconField(botone, bottwo);
		mid = new JPanel();
		mid.add(new JLabel(new ImageIcon("images/victory1.png")));
		bot = new IconField(botone, bottwo);
		
		this.add(top, BorderLayout.NORTH);
		this.add(mid, BorderLayout.CENTER);
		this.add(bot, BorderLayout.SOUTH);
		
	}
	
}
