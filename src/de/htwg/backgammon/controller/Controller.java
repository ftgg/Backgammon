package de.htwg.backgammon.controller;


import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.SpielFeld;
import de.htwg.backgammon.model.Spieler;
import de.htwg.backgammon.model.Player;
import de.htwg.backgammon.model.Stein;
import de.htwg.backgammon.model.Wuerfel;
import de.htwg.backgammon.util.Subject;

public class Controller extends Subject {
	public static final int NEXT = -4;
	private Player s1;
	private Player s2;
	private Player current;
	private Pitch sf;
	private Wuerfel w;
	private int[] zuege = { 0, 0, 0, 0 };
	private MoveVerifier moveVerifier;
	public Controller() {
		sf = new SpielFeld();// Standartgröße = original größe
		w = new Wuerfel();
		createMoveVerifier();
		wuerfeln();
	}

	/**
	 * Controller für Test umgebung
	 * 
	 * @param a
	 */
	public Controller(int i) {
		sf = new SpielFeld(i);
		w = new Wuerfel();
		createMoveVerifier();
		wuerfeln();
		setSpieler("Weiss", "Schwarz");
	}

	public void setSpieler(String n1, String n2) {
		s1 = new Spieler(n1, Stein.WHITE);
		s2 = new Spieler(n2, Stein.BLACK);
		current = s1;
		notifyObs(new GameState(sf, zuege, "Spiel Beginnt", current, false));
	}

	// Auf jedenfall 2 eingaben zb b 3 von bar nach feld nummer 3 oder 20 h für
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
			res[0] = Pitch.BAR;
		} else {
			res[0] = parseInt(s[0]);
		}

		if ("h".equals(s[1])) {
			res[1] = Pitch.EXIT;
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
		boolean win = false;
		String msg = "";
		if (!moveVerifier.checkMove(a, b, zuege, sf, current, s1,s2)) {
			notifyObs(new GameState(sf, zuege, "Nicht möglicher Zug!", current, false));
			return -3;
		}
		int result = sf.move(a, b, current);
		if (result == 111) {
			msg = current.getName() + " hat gewonnen!";
			win = true;
		}

		removeThrow(a, b);
		if (zuegeEmpty())
			spielerwechsel();

		notifyObs(new GameState(sf, zuege, msg, current, win));
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

	private void createMoveVerifier(){
		BarVerifier bv = new BarVerifier();;
		DiceResultVerifier drv = new DiceResultVerifier();
		ExitMoveVerifier emv = new ExitMoveVerifier();
		TargetColorVerifier tcv = new TargetColorVerifier();
		DirectionVerifier dv = new DirectionVerifier();
		bv.successor = drv;
		drv.successor = emv;
		emv.successor = tcv;
		tcv.successor = dv;
		moveVerifier = bv;
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

	public Player[] getSpieler() {
		return new Player[] { s1, s2 };
	}

	public Player getCurrent() {
		return current;
	}

}
