package de.htwg.backgammon.aview.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ColorThemeStandard implements IColorTheme {

	private ImageIcon[] icons;

	
	public ColorThemeStandard(){
		icons = new ImageIcon[12];
		icons[0] = getLightTriangleTop();
		icons[1] = getSelected(SelectIcon.LIGHTTOP);
		
		icons[2] = getLightTriangle();
		icons[3] = getSelected(SelectIcon.LIGHTBOT);
		
		icons[4] = getDarkTriangleTop();
		icons[5] = getSelected(SelectIcon.DARKTOP);
		
		icons[6] = getDarkTriangle();
		icons[7] = getSelected(SelectIcon.DARKBOT);
		
		icons[8] = getBarTop();
		icons[9] = getSelected(SelectIcon.BARTOP);
		
		icons[10] = getBar();
		icons[11] = getSelected(SelectIcon.BARBOT);

	}
	
	
	@Override
	public ImageIcon getDarkTriangle() {
		return ImageString("images/d_dunkel.png");
	}

	@Override
	public ImageIcon getLightTriangle() {
		return ImageString("images/d_hell.png");
	}

	@Override
	public ImageIcon getDarkTriangleTop() {
		return rotateTriangle("images/d_dunkel.png");
	}

	private ImageIcon rotateTriangle(String s) {
		ImageIcon i = ImageString(s);
		BufferedImage bi = new BufferedImage(i.getIconWidth(), i.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();
		AffineTransform at = AffineTransform.getRotateInstance(Math.PI, i.getIconWidth() / 2.0, i.getIconWidth() / 2.0);
		at.translate(0, -500);
		g2.drawImage(i.getImage(), at, null);
		g2.dispose();
		return new ImageIcon(bi);
	}

	@Override
	public ImageIcon getLightTriangleTop() {
		return rotateTriangle("images/d_hell.png");
	}

	@Override
	public ImageIcon getBar() {
		return ImageString("images/Bar.png");
	}

	@Override
	public ImageIcon getBarTop() {
		return rotateTriangle("images/Bar.png");
	}

	@Override
	public ImageIcon getDarkToken() {
		return ImageString("images/Token-Dark.png");
	}

	@Override
	public ImageIcon getLightToken() {
		return ImageString("images/Token-White.png");
	}

	@Override
	public ImageIcon getnoToken() {
		return new ImageIcon("images/empty.png");
	}

	private ImageIcon ImageString(String path) {
		return new ImageIcon(path);
	}

	@Override
	public ImageIcon getDice(int n) {
		String s = "images/" + n + ".png";
		ImageIcon i = new ImageIcon(s);
		i.setImage(i.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
		return i;
	}

	@Override
	public ImageIcon getSelected(SelectIcon pos) {
		String name = pos.toString();
		String s = "images/" + name + ".png";
		System.out.println("greifen zu auf: " + s);
		if (name.contains("TOP"))
			return rotateTriangle(s.replace("TOP", "BOT"));
		return new ImageIcon(s);
	}

	@Override
	public ImageIcon get(int n) {
		assert(n >= icons.length ||n < 0);
		return icons[n];
	}
}
