package de.htwg.backgammon.aview.gui;

import javax.swing.ImageIcon;

public class ColorThemeStandard implements IColorTheme {

	@Override
	public ImageIcon getDarkTriangle() {
		return new ImageIcon("images/darkb.png");
	}

	@Override
	public ImageIcon getLightTriangle() {
		return new ImageIcon("images/lightb.png");
	}

	@Override
	public ImageIcon getDarkTriangleTop() {
		return new ImageIcon("images/dark.png");
	}

	@Override
	public ImageIcon getLightTriangleTop() {
		return new ImageIcon("images/light.png");
	}

	@Override
	public ImageIcon getBar() {
		return new ImageIcon("images/bar.png");
	}

	@Override
	public ImageIcon getDarkToken(int number) {
		if (number == 1)
			return new ImageIcon("images/dark_one.png");
		if (number == 2)
			return new ImageIcon("images/dark_two.png");
		return new ImageIcon("images/dark_more.png");
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

}
