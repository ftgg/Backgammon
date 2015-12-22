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
		ImageIcon ico = new ImageIcon(path);
		return ico;
	}
}
