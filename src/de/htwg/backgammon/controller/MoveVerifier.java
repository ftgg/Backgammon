package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.Player;

public abstract class MoveVerifier {
	MoveVerifier successor;
	public abstract boolean checkMove(int a, int b, int zuege[], Pitch sf, Player s, Player s1, Player s2);
}
