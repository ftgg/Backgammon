package de.htwg.backgammon.aview.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.controller.SelectState;
import de.htwg.backgammon.model.implementation.GameState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class MainPanel extends JPanel implements Observer {

	public static IColorTheme ct;

	private IconField top;
	private JDice mid;
	private IconField bot;
	private GameState gs;
	Controller contr;

	public MainPanel(Controller c) {
		super();
		ct = new ColorThemeStandard();
		contr = c;
		c.add(this);
		this.setLayout(new BorderLayout());

		top = new IconField(0,this, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 26 }, 0);
		mid = new JDice(this);
		bot = new IconField(2,this, new int[] { 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 26 }, 12);
		top.setBackground(new Color(0, 0, 0, 0));
		bot.setBackground(new Color(0, 0, 0, 0));
		mid.setBackground(new Color(0, 0, 0, 0));
		this.add(top, BorderLayout.NORTH);
		this.add(mid, BorderLayout.CENTER);
		this.add(bot, BorderLayout.SOUTH);
	}

	@Override
	public void update(Event e) {
		if (e instanceof GameState) {
			gs = (GameState) e;
			spreadUpdate();
		} else if (e instanceof SelectState) {
			SelectState s = (SelectState) e;
			setclick(s);
		}
	}

	private void setclick(SelectState s) {
		if (s.isTop())
			top.select(s.getIndex() - 1,s.getSelect());
		else
			bot.select(s.getIndex() - 13, s.getSelect());
	}
	

	private void spreadUpdate() {
		top.doUpdate(gs.getWhiteStones(), gs.getBlackStones(), gs.getWhiteBar(), 0);
		mid.doUpdate(gs.getZuege());
		bot.doUpdate(gs.getWhiteStones(), gs.getBlackStones(), gs.getBlackBar(), 12);
		mid.setInfo(gs.getMessage());	
		this.repaint();
	}

	public void resize() {
		mid.repos();
	}
}
