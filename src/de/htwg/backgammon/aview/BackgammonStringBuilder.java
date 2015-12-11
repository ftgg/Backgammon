package de.htwg.backgammon.aview;

import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.GameState;


public class BackgammonStringBuilder implements TuiSB {

	public BackgammonStringBuilder() {

	}

	@Override
	public StringBuilder getInformations(GameState gs) {
		StringBuilder sb = new StringBuilder();
		sb.append("Steine auf der Bar: ");
		sb.append(Bar(gs));
		sb.append("\nWürfel: ");
		int[] a = gs.getZuege();
		for (int i = 0; i < 4; i++) {
			appendDiced(a[i], sb);
		}
		sb.append("\n");
		return sb;
	}

	private void appendDiced(int diceResult, StringBuilder sb) {
		if (diceResult != 0)
			sb.append(diceResult).append(" ");
	}

	private int Bar(GameState gs) {
		if (gs.getCurrent().getColor() == TokenColor.WHITE)
			return gs.getWhiteBar();
		return gs.getBlackBar();
	}

	@Override
	public StringBuilder getStringBuilder(GameState gs) {
		StringBuilder sb = new StringBuilder();
		int[] b = gs.getBlackStones();
		int[] w = gs.getWhiteStones();
		int fieldwidth = b.length / 2;
		int spalte = (fieldwidth) * 3 + 1; // anzahl Spalten * Zeichen pro
											// Spalte + \n
		int fieldSize = 15 * spalte; // 15 Zeilen!

		initSB(sb, fieldSize, spalte);
		setTokens(spalte, w, b, sb);
		printSame(1, spalte - 1, '_', sb);
		printNumbers(2, spalte - 1, fieldwidth, sb);
		printSame(8, spalte - 1, ' ', sb);
		printNumbers(14, spalte - 1, fieldwidth + 1, sb);
		printSame(15, spalte - 1, '¯', sb);

		return sb;
	}

	private void setTokens(int spalte, int[] w, int[] b, StringBuilder sb) {
		for (int i = 3; i < 14; i++)
			printLine(i, spalte - 1, w, b, sb);
	}

	private void initSB(StringBuilder sb, int fieldSize, int spalte) {
		for (int i = 0; i < fieldSize; i++)
			sb.append('X');
		for (int i = 1; i < 16; i++) { // alle Zeilen mit \n beenden
			sb.setCharAt(spalte * i - 1, '\n');
		}
	}

	private void printSame(int zeile, int size, char c, StringBuilder sb) {
		int start = (zeile - 1) * (size + 1);
		for (int i = 0; i < size; i++) {
			sb.setCharAt(start + i, c);
		}
	}

	private void printLine(int zeile, int size, int[] weiss, int[] schwarz, StringBuilder sb) {
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

	private char getWoB(int position, int unrealdepth, int[] w, int[] b) {
		int depth = unrealdepth - 2;
		int i = position;
		if (depth > 5) {// drucken von unten
			i = w.length - (position + 1);
			depth = 12 - depth;
		}
		return color(depth, i, w, b);
	}

	private char color(int depth, int i, int[] w, int[] b) {
		if (w[i] >= depth) {
			return chosechar('W', w[i], depth);
		} else if (b[i] >= depth) {
			return chosechar('B', b[i], depth);
		}
		return ' ';
	}

	private char chosechar(char color, int coloronField, int depth) {
		if (coloronField > 5 && depth == 5)
			return 'X';
		return color;
	}

	private void printNumbers(int zeile, int size, final int absnumber, StringBuilder sb) {
		// ich bin in Zeile zeile und habe eine Zeilenlänge von size, an der
		// stelle size steht das nullzeichen!
		int number = absnumber;
		int start = (zeile - 1) * (size + 1);
		int pos;

		// zahlen von (size-1*3) bis 1
		for (int i = 0; i < size; i++) {
			pos = i % 3;
			if (pos == 0)
				sb.setCharAt(start + i, ' ');
			else if (pos == 1) {
				sb.setCharAt(start + i, getchar(number));
			} else {
				sb.setCharAt(start + i, String.valueOf(number % 10).toCharArray()[0]);
				number = newnumber(number, zeile);
			}
		}
	}

	private int newnumber(int number, int zeile) {
		if (zeile == 2)
			return --number;
		return ++number;
	}

	private char getchar(int number) {
		if (number < 10)
			return ' ';
		return String.valueOf(number).toCharArray()[0];
	}
}
