package de.htwg.backgammon.aview.gui;

import javax.swing.ImageIcon;

public class ColorThemeStandard implements IColorTheme{

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
	public ImageIcon getDarkToken() {
		return new ImageIcon("images/dark_token.png");
	}

	@Override
	public ImageIcon getLightToken() {
		return new ImageIcon("images/light_token.png");
	}

	@Override
	public ImageIcon getnoToken() {
		return new ImageIcon("images/empty.png");
	}

}
