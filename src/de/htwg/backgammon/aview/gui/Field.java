package de.htwg.backgammon.aview.gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
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
	public JLabel foreground;
	public MainPanel mp;

	public Field(ImageIcon icon, int id, MainPanel mp) {
		super();
		this.id = id;
		this.mp = mp;
		Dimension dim = new Dimension(100, 300);
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(dim);
		layeredPane.setLayout(null);

		background = new JLabel(scaleIcon(icon, dim));
		// foreground = new JLabel(scaleIcon(mp.ct.getDarkToken(), dim.width));
		layeredPane.add(background, new Integer(1), 0);
		layeredPane.moveToBack(background);
		// layeredPane.add(foreground, new Integer(2), 0);
		// layeredPane.moveToFront(foreground);

		background.setBounds(0, 0, 100, 300);
		// foreground.setBounds(0, 0, 100, 300);

		// this.add(background);
		// this.add(foreground);
		this.add(layeredPane);
	}

	private ImageIcon scaleIcon(ImageIcon icon, int width) {
		return scaleIcon(icon, new Dimension((int) (width / 1.5), (int) (width / 1.5)));
	}

	private ImageIcon scaleIcon(ImageIcon icon, Dimension dimension) {
		Image img = icon.getImage();
		ImageIcon ii = new ImageIcon(
				img.getScaledInstance(dimension.width, dimension.height, java.awt.Image.SCALE_SMOOTH));
		return ii;
	}

	public int getID() {
		return id;
	}

	public void setTokens(int n, ImageIcon token) {
		for (Component c : layeredPane.getComponentsInLayer(2))
			layeredPane.remove(c);
		this.repaint();
		token = scaleIcon(token, this.getSize().width);
		for (int i = 0; i < n; i++) {
			JLabel newLabel = new JLabel(token);
			layeredPane.add(newLabel, new Integer(2), 0);
			newLabel.setBounds(0 + (int)((this.getWidth() - token.getIconWidth()) / 2.5),
					0 + (i % 5) * (int) (token.getIconHeight() / 1.3) + ((i / 5) * token.getIconHeight() / 2) % 2,
					token.getIconWidth(), token.getIconHeight());
		}

	}

}
