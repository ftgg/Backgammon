package de.htwg.backgammon.aview.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ColorThemeStandard implements IColorTheme {

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
		if (name.contains("TOP"))
			return rotateTriangle(s);
		return new ImageIcon(s);
	}
}
