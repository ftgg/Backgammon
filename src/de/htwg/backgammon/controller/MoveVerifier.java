package de.htwg.backgammon.controller;
import de.htwg.backgammon.model.*;
public abstract class MoveVerifier {
	MoveVerifier successor;
	public abstract boolean checkMove(int a, int b, int zuege[], SpielFeld sf, Spieler s,Controller c);
}
