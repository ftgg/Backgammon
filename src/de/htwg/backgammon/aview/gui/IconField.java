package de.htwg.backgammon.aview.gui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import de.htwg.backgammon.model.TokenColor;

public class IconField extends JPanel{

	private Field[] labels = new Field[13];
	private int[] index = {0,1,2,3,4,5,12,6,7,8,9,10,11}; //cuz last JLabel should be in Center, last is whitespace
	private MainPanel mp; //TODO gibt das tangles?
	
	public IconField(ImageIcon one,ImageIcon two,MainPanel mp){
		this.setLayout(new GridLayout(1,0));
		this.mp = mp;
		
		for(int i = 0; i < 6;i++){
				labels[2*i] = new Field(one, 2*i,mp);
				labels[2*i+1] = new Field(two, 2*i+1,mp);
		}
		labels[12] = new Field(new ImageIcon("images/whitespace.png"),12,mp);
		for(int i = 0; i < 13; i++){
			this.add(labels[index[i]]);
		}
		initActionListeners();
	}
	
	private void initActionListeners(){
		for(int i=0;i<12;i++){
			labels[i].addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					if(e.getSource() instanceof Field){
						Field f = (Field) e.getSource();
						f.mp.select(f.getID(), null, 0);
					}
				}
			});
		}
	}
	
	public void setToken(int id, TokenColor t, int noT){
		labels[id].setIcon(new ImageIcon("images/changed.png"));
	}

	public void select(int id, TokenColor t, int noT) {
		if(t == TokenColor.WHITE)
			labels[id].setIcon(new ImageIcon("images/changed.png"));
		else
			labels[id].setIcon(new ImageIcon("images/changed2.png"));
	}
}
