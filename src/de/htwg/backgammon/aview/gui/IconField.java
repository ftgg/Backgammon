package de.htwg.backgammon.aview.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class IconField extends JPanel {


	private Field[] labels = new Field[13];
	private int[] index; 
	private MainPanel mp;

	public IconField(int top, MainPanel mp, int[] id, int offset) {
		index = new int[] { 11, 10, 9, 8, 7, 6, 12, 5, 4, 3, 2, 1, 0 };
		this.setLayout(new GridLayout(1, 0));
		this.setBackground(new Color(0,0,0,0));
		this.mp = mp;

		for (int i = 0; i < 6; i++) {
			labels[2 * i] = new Field(id[2 * i] + 1, mp,SelectIcon.DARKTOP.ordinal() + top);
			labels[2 * i + 1] = new Field(id[2 * i + 1] + 1, mp,SelectIcon.LIGHTTOP.ordinal() + top);
		}
		labels[12] = new Field( id[id.length - 1], mp,(int)SelectIcon.BARBOT.ordinal());
		if (offset == 0)
			labels[12] = new Field( 25, mp,(int)SelectIcon.BARTOP.ordinal());

		for (int i = 0; i < 13; i++) {
			this.add(labels[index[Math.abs(offset - i)]]);
		}
		initActionListeners();
	}

	private void initActionListeners() {
		for (int i = 0; i < 13; i++) {
			labels[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getSource() instanceof Field) {
						Field f = (Field) e.getSource();
						mp.contr.setclick(f.getID());
					}
				}
			});
		}
	}

	// gain necessary informations to update some JLabels
	public void doUpdate(int[] whitestones, int[] blackstones, int bar, int offset) {
		if (offset > 1)
			doUpdateBot(whitestones, blackstones, bar, offset);
		else
			doUpdateTop(whitestones, blackstones, bar);
	}

	private void doUpdateTop(int[] whitestones, int[] blackstones, int bar) {
		int number;
		for (int i = 0; i < 12; i++) {
			number = blackstones[i];
			if (number > 0) {
				labels[i].setTokens(number, MainPanel.ct.getDarkToken(), 0);
			} else {
				number = whitestones[i];
				if (number > 0)
					labels[i].setTokens(number, MainPanel.ct.getLightToken(), 0);
				else
					labels[i].setTokens(number, MainPanel.ct.getnoToken(), 0);
			}
		}
		labels[12].setTokens(bar, MainPanel.ct.getLightToken(), 0);
	}

	private void doUpdateBot(int[] whitestones, int[] blackstones, int bar, int offset) {
		int number;
		int xOffset = labels[0].getHeight() - 80;
		for (int i = 0; i < 12; i++) {
			number = blackstones[i + offset];
			if (number > 0)
				labels[i].setTokens(number, MainPanel.ct.getDarkToken(), xOffset);
			else {
				number = whitestones[i + offset];
				if (number > 0)
					labels[i].setTokens(number, MainPanel.ct.getLightToken(), xOffset);
				else
					labels[i].setTokens(number, MainPanel.ct.getnoToken(), xOffset);
			}
		}
		labels[12].setTokens(bar, MainPanel.ct.getDarkToken(), xOffset);
	}

	public void select(int in, int b) {
		int labelindex = in;
		if(in > 12)
			labelindex = 12;
		labels[labelindex].updateIcon(b);
	}

}
