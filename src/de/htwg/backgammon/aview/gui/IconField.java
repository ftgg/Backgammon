package de.htwg.backgammon.aview.gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class IconField extends JPanel {

	private Field[] labels = new Field[13];
	private int[] index; // cuz last JLabel should be in Center, last is
							// whitespace
	private MainPanel mp;

	public IconField(ImageIcon one, ImageIcon two, MainPanel mp, int[] id, int offset) {

		index = new int[] { 11, 10, 9, 8, 7, 6, 12, 5, 4, 3, 2, 1, 0 };
		this.setLayout(new GridLayout(1, 0));
		this.mp = mp;

		for (int i = 0; i < 6; i++) {
			labels[2 * i] = new Field(one, id[2 * i] + 1, mp);
			labels[2 * i + 1] = new Field(two, id[2 * i + 1] + 1, mp);
		}
		labels[12] = new Field(mp.ct.getBar(), 25, mp);
		if (offset == 0)
			labels[12] = new Field(mp.ct.getBarTop(), 25, mp);

		for (int i = 0; i < 13; i++) {
			this.add(labels[index[Math.abs(offset - i)]]);
		}
		initActionListeners();
	}

	private void initActionListeners() {
		for (int i = 0; i < 13; i++) {
			labels[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getSource() instanceof Field) {
						Field f = (Field) e.getSource();
						System.out.println("click: " + f.getID());
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
				labels[i].setTokens(number, mp.ct.getDarkToken(), 0);
			} else {
				number = whitestones[i];
				if (number > 0)
					labels[i].setTokens(number, mp.ct.getLightToken(), 0);
				else
					labels[i].setTokens(number, mp.ct.getnoToken(), 0);
			}
		}
		labels[12].setTokens(bar, mp.ct.getLightToken(), 0);
	}

	private void doUpdateBot(int[] whitestones, int[] blackstones, int bar, int offset) {
		int number;
		int xOffset = labels[0].getHeight() - 80;
		for (int i = 0; i < 12; i++) {
			number = blackstones[i + offset];
			if (number > 0)
				labels[i].setTokens(number, mp.ct.getDarkToken(), xOffset);
			else {
				number = whitestones[i + offset];
				if (number > 0)
					labels[i].setTokens(number, mp.ct.getLightToken(), xOffset);
				else
					labels[i].setTokens(number, mp.ct.getnoToken(), xOffset);
			}
		}
		labels[12].setTokens(bar, mp.ct.getDarkToken(), xOffset);
	}

	public void resize() {
		for (Field f : labels)
			f.myresize();
	}

}
