package de.htwg.backgammon.aview.gui;

import javax.swing.ImageIcon;

public interface IColorTheme {

	public ImageIcon getDarkTriangle();
	public ImageIcon getLightTriangle();
	public ImageIcon getDarkTriangleTop();
	public ImageIcon getLightTriangleTop();
	public ImageIcon getBar();
	public ImageIcon getnoToken();
	public ImageIcon getDarkToken(int number);
	public ImageIcon getLightToken(int number);
	
}
