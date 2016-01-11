package de.htwg.backgammon.aview.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class JDice extends JPanel {

	private JLabel d1;
	private JLabel d2;
	private JLabel d3;
	private JLabel d4;
	private JLabel info;
	private JLayeredPane layeredPane;
	MainPanel mp;

	public JDice(MainPanel mainPanel) {
		super();
		this.mp = mainPanel;
		
		d1 = new JLabel(mp.ct.getDice(1));
		d2 = new JLabel(mp.ct.getDice(1));
		d3 = new JLabel(mp.ct.getDice(1));
		d4 = new JLabel(mp.ct.getDice(1));
		
		info = new JLabel();
		info.setFont(new Font(info.getFont().getName(), Font.BOLD, 25));
		
		int width = (mp.ct.getDice(1).getIconWidth() + 5 * 4);
		int height = mp.ct.getDice(1).getIconHeight();
		this.setLayout(null);
	
		
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(4 * width, height));
		layeredPane.setLayout(new FlowLayout());
		
		
		layeredPane.add(info, new Integer(1), 0);
		layeredPane.add(d4, new Integer(1), 0);
		layeredPane.add(d2, new Integer(1), 0);
		layeredPane.add(d1, new Integer(1), 0);
		layeredPane.add(d3, new Integer(1), 0);
		
		layeredPane.setBounds(Gui.winX / 2 + 200,Gui.winY / 2 - 360, 500, 120);
		doUpdate(new int[] { 1, 2, 0, 5 });
		doUpdate(new int[] { 1, 2, 3, 4 });
		this.add(layeredPane);
	}

	public void doUpdate(int[] numbers) {
		d1.setIcon(mp.ct.getDice(numbers[0]));
		d2.setIcon(mp.ct.getDice(numbers[1]));
		d3.setIcon(mp.ct.getDice(numbers[2]));
		d4.setIcon(mp.ct.getDice(numbers[3]));
	}
	
	public void repos(){
		layeredPane.setBounds(mp.getWidth() / 2 -200, mp.getHeight() / 2 -360 , 400, 120);
	}
	
	public void setInfo(String s){
		info.setText(s);
		this.repaint();
	}
}
