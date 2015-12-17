package de.htwg.backgammon.aview.gui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class IconField extends JPanel {

	private Field[] labels = new Field[13];
	private int[] index; // cuz last JLabel should be in Center, last is
							// whitespace
	private MainPanel mp;

	public IconField(ImageIcon one, ImageIcon two, MainPanel mp, int[] id) {
		index = new int[] { 11, 10, 9, 8, 7, 6, 12, 5, 4, 3, 2, 1, 0 };
		this.setLayout(new GridLayout(1, 0));
		this.mp = mp;

		for (int i = 0; i < 6; i++) {
			labels[2 * i] = new Field(one, id[2*i]+1, mp);
			labels[2 * i + 1] = new Field(two,id[2*i+1]+1, mp);
		}
		labels[12] = new Field(new ImageIcon("images/whitespace.png"), 12, mp);
		for (int i = 0; i < 13; i++) {
			this.add(labels[index[i]]);
		}
		initActionListeners();
	}

	private void initActionListeners() {
		for (int i = 0; i < 12; i++) {
			labels[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getSource() instanceof Field) {
						Field f = (Field) e.getSource();
						mp.contr.setclick(f.getID());
					}
				}
			});
		}
	}
	//gain necessary informations to update some JLabels
	public void doUpdate(int[] blackstones, int offset){
		for(int i=0+offset;i<12+offset;i++){
			if(blackstones[i] > 0)
				labels[i].setIcon(new ImageIcon("images/dark_one_light.png"));
		}
	}
}
