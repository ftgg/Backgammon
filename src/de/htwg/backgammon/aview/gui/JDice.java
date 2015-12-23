package de.htwg.backgammon.aview.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class JDice extends JPanel {

	private JLabel d1;
	private JLabel d2;
	private JLabel d3;
	private JLabel d4;
	private JLayeredPane layeredPane;
	MainPanel mp;

	public JDice(MainPanel mainPanel) {
		super();
		this.mp = mainPanel;
		d1 = new JLabel(mp.ct.getDice(1));
		d2 = new JLabel(mp.ct.getDice(1));
		d3 = new JLabel(mp.ct.getDice(1));
		d4 = new JLabel(mp.ct.getDice(1));
		layeredPane = new JLayeredPane();
		int width = (mp.ct.getDice(1).getIconWidth() + 5 * 4);
		int height = mp.ct.getDice(1).getIconHeight();
		// System.out.println("Hallo: " + (d1.getWidth() + 10) * 4 + " // " +
		// d1.getHeight()+" // "+mp.ct.getDice(1).getIconWidth());
		layeredPane.setPreferredSize(new Dimension(4 * width, height));
		layeredPane.setLayout(new FlowLayout());

		layeredPane.add(d4, new Integer(1), 0);
		layeredPane.add(d2, new Integer(1), 0);
		layeredPane.add(d1, new Integer(1), 0);
		layeredPane.add(d3, new Integer(1), 0);
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
}
