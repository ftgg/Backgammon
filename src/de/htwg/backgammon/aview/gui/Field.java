package de.htwg.backgammon.aview.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Field extends JPanel {

	private static final long serialVersionUID = 1L;
	private int id;
	private JLayeredPane layeredPane;
	private JLabel background;
	private MainPanel mp;
	private int icoType;

	public Field(int id, MainPanel mp, int icoType) {
		super();
		ImageIcon icon = mp.ct.get(icoType);
		this.id = id;
		this.mp = mp;
		this.icoType = icoType;
		this.setBackground(new Color(0, 0, 0, 0));
		Dimension dim = new Dimension(100, 300);
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(dim);
		layeredPane.setLayout(null);

		background = new JLabel(scaleIcon(icon, dim));
		layeredPane.add(background, new Integer(1), 0);
		layeredPane.moveToBack(background);

		background.setBounds(0, 0, 100, 300);
		this.add(layeredPane);
	}

	private ImageIcon scaleIcon(ImageIcon icon, int width) {
		return scaleIcon(icon, new Dimension((int) (width / 1.5), (int) (width / 1.5)));
	}

	private ImageIcon scaleIcon(ImageIcon icon, Dimension dimension) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(dimension.width, dimension.height, java.awt.Image.SCALE_SMOOTH));
	}

	public int getID() {
		return id;
	}

	public void setTokens(int n, ImageIcon token, int offset) {
		for (Component c : layeredPane.getComponentsInLayer(2))
			layeredPane.remove(c);
		this.repaint();
		ImageIcon scaledtoken = scaleIcon(token, 100);
		for (int i = 0; i < n; i++) {
			JLabel newLabel = new JLabel(scaledtoken);
			layeredPane.add(newLabel, new Integer(2), 0);
			newLabel.setBounds((int) ((this.getWidth() - scaledtoken.getIconWidth()) / 2.5), Math.abs(offset
					- ((i % 5) * (int) (scaledtoken.getIconHeight() / 1.3) + ((i / 5) * scaledtoken.getIconHeight() / 2) % 2)),
					scaledtoken.getIconWidth(), scaledtoken.getIconHeight());
		}
	}

	public void setIcon(ImageIcon icon) {
		ImageIcon scaledicon = scaleIcon(icon, new Dimension(100, 300));
		background.setIcon(scaledicon);
		mp.repaint();

	}

	public void updateIcon(int b) {
		setIcon(mp.ct.get(icoType + b));
	}

}
