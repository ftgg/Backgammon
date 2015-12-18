package de.htwg.backgammon.aview.gui;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ColorThemeStandard implements IColorTheme {

	@Override
	public ImageIcon getDarkTriangle() {
		return ImageString("images/darkb.png");
	}



	@Override
	public ImageIcon getLightTriangle() {
		return ImageString("images/lightb.png");
	}

	@Override
	public ImageIcon getDarkTriangleTop() {
		return ImageString("images/dark.png");
	}

	@Override
	public ImageIcon getLightTriangleTop() {
		return ImageString("images/light.png");
	}

	@Override
	public ImageIcon getBar() {
		return ImageString("images/bar.png");
	}

	@Override
	public ImageIcon getDarkToken(int number) {
		String path = "images/dark_more.png";
		if (number == 1)
			path = "images/dark_one.png";
		if (number == 2)
			path = "images/dark_two.png";
		return ImageString(path);
	}

	@Override
	public ImageIcon getLightToken(int number) {
		if (number == 1)
			return new ImageIcon("images/light_one.png");
		if (number == 2)
			return new ImageIcon("images/light_two.png");
		return new ImageIcon("images/light_more.png");
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
