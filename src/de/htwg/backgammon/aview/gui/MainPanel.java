package de.htwg.backgammon.aview.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.controller.GameState;
import de.htwg.backgammon.model.TokenColor;

public class MainPanel extends JPanel{

	private IconField top;
	private JPanel mid;
	private IconField bot;
	private int size;
	
	private int selectedID;
	private GameState gs;
	private Controller contr;
	
	public MainPanel(Controller c){
		contr = c;
		size = 24;
		this.setLayout(new BorderLayout());
		ImageIcon botone = new ImageIcon("images/darkb.png");
		ImageIcon bottwo = new ImageIcon("images/lightb.png");
		ImageIcon topone = new ImageIcon("images/dark.png");
		ImageIcon toptwo = new ImageIcon("images/light.png");
		
		top = new IconField(topone, toptwo);
		//top = new JPanel();
		//top.add(new JLabel(new ImageIcon("images/dark.png")));
		mid = new JPanel();
		mid.add(new JLabel(new ImageIcon("images/nomessage.png")));
		bot = new IconField(botone, bottwo);
		//this.add(new JLabel(new ImageIcon("images/dark.png")));
		this.add(top, BorderLayout.NORTH);
		this.add(mid, BorderLayout.CENTER);
		this.add(bot, BorderLayout.SOUTH);
	}
	
	public void setToTriangle(int id,TokenColor t, int noT){
		int index = id % (size/2);
		if(id > size/2)
			bot.setToken(index, t, noT);
		else
			top.setToken(index, t, noT);
	}
	public void removeFromTriangle(int id,TokenColor t){
		
	}
	
	private void select(int id){
		if(selectedID < 0){
			selectedID = id;
		}
		else{
			contr.doAction(selectedID + " " + id);
			selectedID = -1;
		}
	}
	
}
