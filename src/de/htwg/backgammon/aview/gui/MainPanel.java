package de.htwg.backgammon.aview.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.controller.GameState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class MainPanel extends JPanel implements Observer{

	private IconField top;
	private JPanel mid;
	private IconField bot;
	private int size;
	private GameState gs;
	Controller contr;
	
	public MainPanel(Controller c){
		super();
		contr = c;
		c.add(this);
		size = 24;
		this.setLayout(new BorderLayout());
		ImageIcon botone = new ImageIcon("images/darkb.png");
		ImageIcon bottwo = new ImageIcon("images/lightb.png");
		ImageIcon topone = new ImageIcon("images/dark.png");
		ImageIcon toptwo = new ImageIcon("images/light.png");

		top = new IconField(topone, toptwo,this,new int[]{0,1,2,3,4,5,6,7,8,9,10,11,24});
		//top = new JPanel();
		//top.add(new JLabel(new ImageIcon("images/dark.png")));
		mid = new JPanel();
		mid.add(new JLabel(new ImageIcon("images/nomessage.png")));
		bot = new IconField(botone, bottwo,this,new int[]{23,22,21,20,19,18,17,16,15,14,13,12,25});
		//this.add(new JLabel(new ImageIcon("images/dark.png")));
		this.add(top, BorderLayout.NORTH);
		this.add(mid, BorderLayout.CENTER);
		this.add(bot, BorderLayout.SOUTH);
		//top.doUpdate(new int[]{0,0,0,0,0,0,0,1,1,1,1,0,0},0); klappt
	}

	@Override
	public void update(Event e) {
		if (e instanceof GameState) {
			gs = (GameState) e;
			System.out.println(gs.getZuege());
			spreadUpdate();
		}
	}
	
	private void spreadUpdate(){
		top.doUpdate(gs.getBlackStones(),0);
		//TODO mid
		bot.doUpdate(gs.getBlackStones(),12);
	}
}
