package de.htwg.backgammon.aview.gui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IconField extends JPanel{

	public IconField(ImageIcon one,ImageIcon two){
		this.setLayout(new GridLayout(1,0));
		
		for(int i = 0; i < 7;i++){
			if(i == 3){
				this.add(new JLabel(new ImageIcon("images/whitespace.png")));
			}
			else{
				this.add(new JLabel(one));
				this.add(new JLabel(two));
			}
		}
	}
}
