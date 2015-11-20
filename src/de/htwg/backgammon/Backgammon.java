package de.htwg.backgammon;

import de.htwg.backgammon.aView.Tui;
import de.htwg.backgammon.controller.Controller;

public class Backgammon {

	public static void main(String[] args) {
		// TODO schichten aufbauen (controller,model usw..)

		// Controller c = new Controller();
		// Tui tui = new Tui(c);

		printNumbers(12, false);
		printNumbers(24, true);
	}

	private static void printNumbers(int a, boolean left) {
		StringBuilder sb = new StringBuilder();
		if (left) {
			for (int i = a/2 + 1; i <= a; i++) {
				sb.append(whitespace(i));
			}
		} else {
			for (int i = a; i > 0; i--) {
				sb.append(whitespace(i));
			}
		}
		System.out.println(sb.toString());
	}

	private static String whitespace(int i) {
		if (i < 10)
			return "  " + i;
		return " " + i;
	}
}
