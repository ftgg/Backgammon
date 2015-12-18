package de.htwg.backgammon.aview.gui;

import java.awt.Image;

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
		return ImageString("images/d_dunkel.png");
	}

	@Override
	public ImageIcon getLightTriangleTop() {
		return ImageString("images/d_hell.png");
	}

	@Override
	public ImageIcon getBar() {
		return ImageString("images/empty.png");
	}

	@Override
	public ImageIcon getDarkToken(int number) {
		String path = "images/empty.png";
//		if (number == 1)
//			path = "images/dark_one.png";
//		if (number == 2)
//			path = "images/dark_two.png";
		return ImageString(path);
	}

	@Override
	public ImageIcon getLightToken(int number) {
//		if (number == 1)
//			return new ImageIcon("images/light_one.png");
//		if (number == 2)
//			return new ImageIcon("images/light_two.png");
		return ImageString("images/empty.png");
	}

	@Override
	public ImageIcon getnoToken() {
		return new ImageIcon("images/empty.png");
	}
	
	private ImageIcon ImageString(String path) {
		ImageIcon ico = new ImageIcon(path);
		ico.setImage(ico.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
		return ico;
	}
}
