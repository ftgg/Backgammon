package de.htwg.backgammon.aView;

import de.htwg.backgammon.controller.GameState;
import de.htwg.backgammon.model.Stein;

public class BackgammonStringBuilder {

	public BackgammonStringBuilder() {

	}

	public StringBuilder getInformations(GameState gs) {
		StringBuilder sb = new StringBuilder();
		sb.append("Steine auf der Bar: ");
		if (gs.getCurrent().getColor() == Stein.getWhite())
			sb.append(gs.getWhiteBar());
		else
			sb.append(gs.getBlackBar());
		sb.append("\nWürfel: ");
		int[] a = gs.getZuege();
		for (int i = 0; i < 4; i++) {
			if (a[i] != 0) {
				sb.append(a[i]).append(" ");
			}
		}
		sb.append("\n");
		return sb;
	}

	public StringBuilder getStringBuilder(GameState gs) {
		StringBuilder sb = new StringBuilder();
		int[] b = gs.getBlackStones();
		int[] w = gs.getWhiteStones();

		int fieldwidth = b.length / 2;

		int spalte = (fieldwidth) * 3 + 1; // anzahl Spalten * Zeichen pro
											// Spalte + \n
		int fieldSize = 15 * spalte; // 15 Zeilen!

		for (int i = 0; i < fieldSize; i++)
			sb.append('X');

		for (int i = 1; i < 16; i++) { // alle Zeilen mit \n beenden
			sb.setCharAt(spalte * i - 1, '\n');
		}

		for (int i = 3; i < 14; i++) {
			printLine(i, spalte - 1, w, b, sb);
		}
		printSame(1, spalte - 1, '_', sb);
		printNumbers(2, spalte - 1, fieldwidth, sb);
		printSame(8, spalte - 1, ' ', sb);
		printNumbers(14, spalte - 1, fieldwidth + 1, sb);
		printSame(15, spalte - 1, '¯', sb);

		return sb;
	}

	private static void printSame(int zeile, int size, char c, StringBuilder sb) {
		int start = (zeile - 1) * (size + 1);
		for (int i = 0; i < size; i++) {
			sb.setCharAt(start + i, c);
		}
	}

	private static void printLine(int zeile, int size, int[] weiss, int[] schwarz, StringBuilder sb) {
		int start = (zeile - 1) * (size + 1);
		int pos;
		for (int i = 0; i < size; i++) {
			pos = i % 3;
			if (pos == 2) {
				sb.setCharAt(start + i, getWoB((weiss.length / 2 - 1) - i / 3, zeile, weiss, schwarz));
			} else {
				sb.setCharAt(start + i, ' ');
			}
		}
	}

	private static char getWoB(int position, int unrealdepth, int[] w, int[] b) {
		int depth = unrealdepth - 2;
		int i = position;
		if (depth > 5) {// drucken von unten
			i = w.length - (position + 1);
			depth = 12 - depth;
		}
		if (w[i] >= depth) {
			return chosechar('W', w[i], depth);
		}
		if (b[i] >= depth) {
			return chosechar('B', b[i], depth);
		}
		return ' ';
	}

	private static char chosechar(char color, int coloronField,int depth){
		if (coloronField > 5 && depth == 5)
			return 'X';
		return color;
	}
	
	private static void printNumbers(int zeile, int size, int number, StringBuilder sb) {
		// ich bin in Zeile zeile und habe eine Zeilenlänge von size, an der
		// stelle size steht das nullzeichen!
		// (zeile-1)*size = startposition
		int start = (zeile - 1) * (size + 1);
		int pos;

		// zahlen von (size-1*3) bis 1
		for (int i = 0; i < size; i++) {
			pos = i % 3;
			if (pos == 0)
				sb.setCharAt(start + i, ' ');
			else if (pos == 1) {
				sb.setCharAt(start + i, getchar(number));
			} else if (pos == 2) {
				sb.setCharAt(start + i, String.valueOf(number % 10).toCharArray()[0]);
				if (zeile == 2)
					number--;
				else
					number++;
			}
		}
	}

	private static char getchar(int number) {
		if (number < 10)
			return ' ';
		return String.valueOf(number).toCharArray()[0];
	}

}
