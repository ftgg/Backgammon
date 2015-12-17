package de.htwg.backgammon.controller;

import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;
import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.Pitch;
import de.htwg.backgammon.model.implementation.Player;
import de.htwg.backgammon.model.implementation.Dice;
import de.htwg.backgammon.util.Subject;

public class Controller extends Subject {
	public static final int NEXT = -4;
	private IPlayer s1;
	private IPlayer s2;
	private IPlayer current;
	private IPitch sf;
	private Dice w;
	private int[] zuege = { 0, 0, 0, 0 };
	private MoveVerifier moveVerifier;
	private ActionParser actionparser;

	private int lastclick; // only need with gui

	public Controller() {
		sf = new Pitch();// Standartgröße = original größe
		w = new Dice();
		lastclick = -1;
		createMoveVerifier();
		actionparser = new ActionParser();
		wuerfeln();
	}

	/**
	 * Controller für Test umgebung
	 * 
	 * @param a
	 */
	public Controller(int i) {
		sf = new Pitch(i);
		w = new Dice();
		createMoveVerifier();
		actionparser = new ActionParser();
		wuerfeln();
		setSpieler("Weiss", "Schwarz");
	}

	public void setSpieler(String n1, String n2) {
		s1 = new Player(n1, TokenColor.WHITE);
		s2 = new Player(n2, TokenColor.BLACK);
		current = s1;
		notifyObs(new GameState(sf, zuege, "Spiel Beginnt", current, false));
	}

	public void doAction(String s) {
		int[] act = actionparser.parse(s);

		System.out.println("do action mit: "+act[0]+" "+act[1]);
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

	public void wuerfeln() {
		w.wuerfeln();
		if (w.isDoublets()) {
			zuege[0] = w.getCurrentCubeNumbers()[0];
			zuege[1] = w.getCurrentCubeNumbers()[1];
			zuege[2] = w.getCurrentCubeNumbers()[0];
			zuege[3] = w.getCurrentCubeNumbers()[1];
		} else {
			zuege[0] = w.getCurrentCubeNumbers()[0];
			zuege[1] = w.getCurrentCubeNumbers()[1];
			zuege[2] = 0;
			zuege[3] = 0;
		}
	}

	public int spielZug(int a, int b) {
		boolean win = false;
		String msg = "";
		if (!moveVerifier.checkMove(a, b, zuege, sf, current, s1, s2)) {
			notifyObs(new GameState(sf, zuege, "Nicht möglicher Zug!", current, false));
			return -3;
		}
		int result = sf.move(a, b, current);
		if (result == 111) {
			msg = current.getName() + " hat gewonnen!";
			win = true;
		}

		// removeThrow(a, b);
		if (zuegeEmpty())
			spielerwechsel();

		notifyObs(new GameState(sf, zuege, msg, current, win));
		return result;
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

	private void createMoveVerifier() {
		BarVerifier bv = new BarVerifier();
		;
		DiceResultVerifier drv = new DiceResultVerifier();
		ExitMoveVerifier emv = new ExitMoveVerifier();
		TargetColorVerifier tcv = new TargetColorVerifier();
		DirectionVerifier dv = new DirectionVerifier();
		moveVerifier = drv;
		drv.successor = bv;
		bv.successor = emv;
		emv.successor = tcv;
		tcv.successor = dv;
	}

	/**
	 * NUR für tests, nicht zum gebrauch gedacht =)
	 * 
	 * @return Wuerfelergebnis
	 */
	public int[] getWuerfelC() {
		return w.getCurrentCubeNumbers();
	}

	public int[] getZuege() {
		return zuege;
	}

	public IPlayer[] getSpieler() {
		return new IPlayer[] { s1, s2 };
	}

	public IPlayer getCurrent() {
		return current;
	}

	// ab hier änderungen, vorsicht weil git merge und so
	// TODO gui wurde geklickt.
	public void setclick(int id) {

		System.out.println("click: " + id);
		if (lastclick == -1) {
			lastclick = id;
		} else {
			System.out.println(toStr(lastclick, id));
			doAction(toStr(lastclick, id));
			lastclick = -1;
		}
	}

	private String toStr(int a, int b) {
		String first;
		if (a == 24 || a == 24)
			first = "b ";
		else
			first = a + " ";
		if (b == 24 || b == 24)
			return first + "h";
		return first + b;
	}
}
