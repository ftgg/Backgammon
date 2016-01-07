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
		ImageIcon ii = new ImageIcon(
				img.getScaledInstance(dimension.width, dimension.height, java.awt.Image.SCALE_SMOOTH));
		return ii;
	}

	public int getID() {
		return id;
	}

	public void setTokens(int n, ImageIcon token,int offset) {
		for (Component c : layeredPane.getComponentsInLayer(2))
			layeredPane.remove(c);
		this.repaint();
		token = scaleIcon(token, this.getSize().width);
		for (int i = 0; i < n; i++) {
			JLabel newLabel = new JLabel(token);
			layeredPane.add(newLabel, new Integer(2), 0);
			newLabel.setBounds(((int)((this.getWidth() - token.getIconWidth()) / 2.5)),
					Math.abs(offset - ((i % 5) * (int) (token.getIconHeight() / 1.3) + ((i / 5) * token.getIconHeight() / 2) % 2)),
					token.getIconWidth(), token.getIconHeight());
		}

	}
	
	public final double DicePt = 12.5;
    public void myresize(){
    	int heightofField = (int)(mp.getHeight() / 2) * (int)(100 - DicePt / 2)/100;
		this.setSize(mp.getWidth() / 13, heightofField);
		layeredPane.setSize(this.getSize());
		ImageIcon img = scaleIcon((ImageIcon)background.getIcon(),new Dimension(mp.getWidth() / 13, heightofField));
		background.setIcon(img);
		this.repaint();
		//this.setBounds(0,mp.getHeight(),100,300);
		System.out.println(this.getSize().getSize().toString());
	}

	public void setIcon(ImageIcon i) {
		//TODO DIMENSION new Dimension(this.getHeight(), this.getWidth())
		background = new JLabel(scaleIcon(i, new Dimension(100, 300)));
	}

}
