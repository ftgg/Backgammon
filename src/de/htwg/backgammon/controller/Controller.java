package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.*;
import de.htwg.backgammon.util.Subject;

public class Controller extends Subject {
	private static final int NEXT = -4;
	private Spieler s1;
	private Spieler s2;
	private Spieler current;
	private SpielFeld sf;
	private Wuerfel w;
	private int[] zuege = { 0, 0, 0, 0 };

	public Controller() {
		sf = new SpielFeld();// Standartgr��e = original gr��e
		w = new Wuerfel();
		wuerfeln();
	}

	/**
	 * Controller f�r Test umgebung
	 * 
	 * @param a
	 */
	public Controller(int i) {
		sf = new SpielFeld(i);
		w = new Wuerfel();
		wuerfeln();
		setSpieler("Weiss", "Schwarz");
	}

	public void setSpieler(String n1, String n2) {
		s1 = new Spieler(n1, Stein.getWhite());
		s2 = new Spieler(n2, Stein.getBlack());
		current = s1;
		notifyObs(new GameState(sf, zuege, "Spiel Beginnt", current, false));
	}

	// Auf jedenfall 2 eingaben zb b 3 von bar nach feld nummer 3 oder 20 h f�r
	// von 20 nach hause, oder 3 5
	public void doAction(String s) {
		int[] act = parseAction(s);
		if (act[0] == -3 || act[1] == -3) {
			notifyObs(new GameState(sf, zuege, "Fehlerhafte Eingabe!", current, false));
			return;
		} else if (act[0] == NEXT) {
			spielerwechsel();
			notifyObs(new GameState(sf, zuege, "SpielerWechsel", current, false));
			return;
		}
		spielZug(act[0], act[1]);
	}

	// public for test
	/**
	 * 
	 * @param act
	 *            String with two numbers or "h" | "b"
	 * @return two int's and -3 if illegalArgument
	 */

	public int[] parseAction(String act) {
		String[] s = act.split(" ");

		if ("n".equals(s[0]))
			return new int[] { NEXT, NEXT };

		if (s.length != 2)
			return new int[] { -3, -3 };
		int[] res = { 0, 0 };

		if ("b".equals(s[0])) {
			res[0] = sf.getBar();
		} else {
			res[0] = parseInt(s[0]);
		}

		if ("h".equals(s[1])) {
			res[1] = sf.getExit();
		} else {
			res[1] = parseInt(s[1]);
		}
		return res;
	}

	int parseInt(String s) {
		int a;
		try {
			a = Integer.parseInt(s);
		} catch (Exception e) {
			System.err.println(e);
			return -3;
		}
		if (a < 1)
			return -3;
		return a - 1;
	}

	public void wuerfeln() {
		w.wuerfeln();
		if (w.isPasch()) {
			zuege[0] = w.getCurrent()[0];
			zuege[1] = w.getCurrent()[1];
			zuege[2] = w.getCurrent()[0];
			zuege[3] = w.getCurrent()[1];
		} else {
			zuege[0] = w.getCurrent()[0];
			zuege[1] = w.getCurrent()[1];
			zuege[2] = 0;
			zuege[3] = 0;
		}
	}

	public int spielZug(int a, int b) {
		if (!verifyMove(a, b)) {
			notifyObs(new GameState(sf, zuege, "Nicht m�glicher Zug!", current, false));
			return -3;
		}
		int result = sf.zug(a, b, current);
		if (result == 111) {
			notifyObs(new GameState(sf, zuege, current.getName() + " hat gewonnen!", current, true));
			return result;
		}

		removeThrow(a, b);
		if (zuegeEmpty())
			spielerwechsel();

		notifyObs(new GameState(sf, zuege, "", current, false));
		return result;
	}

	/**
	 * deleates the current move from zuege
	 */
	protected void removeThrow(int a, int b) {
		int digit = Math.abs(a - b);
		for (int i = 0; i < 4; i++)
			if (zuege[i] == digit) {
				zuege[i] = 0;
				break;
			}
	}

	/**
	 * checks if move from a to b is possible with diced numbers
	 * 
	 * @param a
	 *            startposition
	 * @param b
	 *            targetposition
	 * @return true if move is possible with diced numbers
	 */
	protected boolean verifyMove(int a, int b) {
		return inDiceResult(a, b) && isBarMoveValid(a, b) && isExitMoveValid(b) && sf.isMovePossible(a, b, current)
				&& isDirectionValid(a, b);
	}

	public boolean isDirectionValid(int a, int b) {
		boolean color = (a - b < 0) && (current.getColor() == Stein.getWhite());
		return (a - b > 0) && current.getColor() == Stein.getBlack() || color || b == sf.getExit();
	}

	public boolean isExitMoveValid(int b) {
		return !(b == sf.getExit()) || sf.allHome(current); // Implikation
	}

	public boolean inDiceResult(int a, int b) {
		int value = getDistance(a, b);
		int max = 0;
		boolean indiceResult = true;
		for (int i : zuege) {
			indiceResult = (value != i && indiceResult);
			max = Math.max(max, i);
		}
		if (b == SpielFeld.getExit())
			return max >= value;
		return !indiceResult;
	}

	public int getDistance(int a, int b) {
		int start = sf.getSize();
		int end = -1;
		if (current.getColor() == Stein.getWhite()) {
			start = 0;
			end = sf.getSize();
		}
		if (a == sf.getBar())
			a = start;
		else if (b == sf.getExit())
			b = end;
		return Math.abs(b - a);
	}

	private boolean isBarMoveValid(int a, int b) {
		boolean isBarEmpty = sf.isBarEmpty(current);
		boolean aisbar = a == sf.getBar();
		boolean indexInHome = sf.indexInHome(b, otherPlayer(current));
		return isBarEmpty || aisbar && indexInHome;
	}

	Spieler otherPlayer(Spieler c) {
		return c == s1 ? s2 : s1;
	}

	public boolean zuegeEmpty() {
		for (int c : zuege) {
			if (c != 0)
				return false;
		}
		return true;
	}

	public void spielerwechsel() {
		if (current == s1)
			current = s2;
		else
			current = s1;
		wuerfeln();
	}

	/**
	 * NUR EINE TESTMETHODE nicht zum gebrauch gedacht =)
	 * 
	 * @return Wuerfelergebnis
	 */
	public int[] getWuerfelC() {
		return w.getCurrent();
	}

	public int[] getZuege() {
		return zuege;
	}

	public Spieler[] getSpieler() {
		return new Spieler[] { s1, s2 };
	}

	public Spieler getCurrent() {
		return current;
	}
	
	public static int getNext() {
		return NEXT;
	}
}
