package de.htwg.backgammon.aview.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.model.implementation.GameState;
import de.htwg.backgammon.model.implementation.SelectState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class MainPanel extends JPanel implements Observer {

	public IColorTheme ct;

	private IconField top;
	private JDice mid;
	private IconField bot;
	private int size;
	private GameState gs;
	Controller contr;

	public MainPanel(Controller c) {
		super();
		ct = new ColorThemeStandard();
		contr = c;
		c.add(this);
		size = 24;
		this.setLayout(new BorderLayout());

		final ImageIcon botone = ct.getDarkTriangle();
		final ImageIcon bottwo = ct.getLightTriangle();
		final ImageIcon topone = ct.getDarkTriangleTop();
		final ImageIcon toptwo = ct.getLightTriangleTop();

		top = new IconField(topone, toptwo, this, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 24 }, 0);
		mid = new JDice(this);
		bot = new IconField(botone, bottwo, this, new int[] { 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 25 }, 12);
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK,10));
		top.setBackground(new Color(0,0,0,0));
		bot.setBackground(new Color(0,0,0,0));
		mid.setBackground(new Color(0,0,0,0));
		this.add(top,BorderLayout.NORTH);
		this.add(mid,BorderLayout.CENTER);
		this.add(bot,BorderLayout.SOUTH);
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
			top.select(s.getIndex(), getIcon(s));
		else
			bot.select(s.getIndex(), getIcon(s));
	}

	//TODO if(works){reduce complexity}
	private ImageIcon getIcon(SelectState s) {
		ImageIcon setit;
		if (s.isTop()) {
			if (s.getIndex() % 2 == 0) {
				if (s.getSelect())
					setit = ct.getSelected(SelectIcon.LIGHTTOP);
				else
					setit = ct.getLightTriangleTop();
			} else {
				if (s.getSelect())
					setit = ct.getSelected(SelectIcon.DARKTOP);
				else
					setit = ct.getDarkTriangle();
			}
		} else {
			if (s.getIndex() % 2 == 0) {
				if (s.getSelect())
					setit = ct.getSelected(SelectIcon.DARKBOT);
				else
					setit = ct.getDarkTriangle();
			} else {
				if (s.getSelect())
					setit = ct.getSelected(SelectIcon.LIGHTTOP);
				else
					setit = ct.getLightTriangle();
			}
		}
		return setit;
	}

	private void spreadUpdate() {
		System.out.println(gs.getZuege()[0] + " " + gs.getZuege()[1]);
		top.doUpdate(gs.getWhiteStones(), gs.getBlackStones(), gs.getWhiteBar(), 0);
		mid.doUpdate(gs.getZuege());
		bot.doUpdate(gs.getWhiteStones(), gs.getBlackStones(), gs.getBlackBar(), 12);
		this.repaint();
	}
	
    public void resize(){
    	mid.repos();
    }
}
